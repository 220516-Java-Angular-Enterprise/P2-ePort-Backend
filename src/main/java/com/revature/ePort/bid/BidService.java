package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.bid.dtos.responses.ViewBidHistory;
import com.revature.ePort.user.User;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import com.revature.ePort.util.custom_exception.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
public class BidService {
    @Inject
    private final BidRepository bidRepository;

    @Inject
    @Autowired
    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public void addNewBid(NewBid newBid){
        if(!auctionExists(newBid.getAuction_id())) throw new ResourceConflictException("Auction doesn't exist");
        if(!auctionIsActive(newBid.getAuction_id()))throw new InvalidRequestException("Auction is no longer active");
        if(!correctPricing(newBid.getAmount(),newBid.getAuction_id())) throw new ResourceConflictException("Amount not valid");
        if(bidExists(newBid.getAuction_id(), newBid.getUser_id())){
            updateBid(newBid);
            bidRepository.removeFunds(newBid.getAmount(), newBid.getUser_id());
            return;
        }
        if(buyOutBid(newBid.getAmount(), newBid.getAuction_id())){
            newBid.setAmount(bidRepository.buyOut(newBid.getAuction_id()));
            long today = System.currentTimeMillis();
            Timestamp orderSent = new Timestamp(today+15);
            Timestamp orderArrived = new Timestamp(today+45);
            String shippingAddress = bidRepository.retrieveShippingAddress(newBid.getUser_id());
            bidRepository.addNewBid(newBid.getAuction_id(),newBid.getUser_id(),newBid.getAmount());
            bidRepository.closeAuction(newBid.getAuction_id());
            bidRepository.createNewOrder(UUID.randomUUID().toString(), orderArrived, orderSent, "Preparing to package", shippingAddress);
            bidRepository.removeFunds(newBid.getAmount(),newBid.getUser_id());
            return;
        }
        bidRepository.addNewBid(newBid.getAuction_id(), newBid.getUser_id(),newBid.getAmount());
        bidRepository.removeFunds(newBid.getAmount(), newBid.getUser_id());
    }

    public void updateBid(NewBid updateBid){
        if(!correctPricing(updateBid.getAmount(),updateBid.getAuction_id())) throw new ResourceConflictException("Amount not valid");
        if(buyOutBid(updateBid.getAmount(), updateBid.getAuction_id())){
            updateBid.setAmount(bidRepository.buyOut(updateBid.getAuction_id()));
            long today = System.currentTimeMillis();
            Timestamp orderSent = new Timestamp(today+15);
            Timestamp orderArrived = new Timestamp(today+45);
            String shippingAddress = bidRepository.retrieveShippingAddress(updateBid.getUser_id());
            bidRepository.updateBid(updateBid.getAmount(),updateBid.getUser_id(),updateBid.getAuction_id());
            bidRepository.closeAuction(updateBid.getAuction_id());
            String id = UUID.randomUUID().toString();
            bidRepository.createNewOrder(id, orderArrived, orderSent, "Preparing to package", shippingAddress);
            bidRepository.orderIdOnBid(id, updateBid.getAuction_id());
        }
        bidRepository.updateBid(updateBid.getAmount(),updateBid.getUser_id(),updateBid.getAuction_id());
    }

    public List<Bid> getFinalBids(String id){
        return bidRepository.closingBids(id);
    }

    public void closeBid(String auctionID, String userID){
        String id = UUID.randomUUID().toString();
        long today = System.currentTimeMillis();
        Timestamp orderSent = new Timestamp(today+15);
        Timestamp orderArrived = new Timestamp(today+45);
        String shippingAddress = bidRepository.retrieveShippingAddress(userID);
        bidRepository.closeAuction(auctionID);
        bidRepository.createNewOrder(id, orderArrived, orderSent, "Preparing to package", shippingAddress);
        bidRepository.orderIdOnBid(id, auctionID);
    }

    private boolean auctionExists(String auctionID){
        return bidRepository.auctionExists(auctionID) != null;
    }

    private boolean bidExists(String auctionID, String userID){
        return bidRepository.bidExists(auctionID, userID) != null;
    }

    private boolean correctPricing(BigDecimal num, String auctionID){
        BigDecimal i = bidRepository.maxAmount(auctionID);
        if(i==null) return true;
        return num.compareTo(i) > 0;
    }

    private boolean buyOutBid(BigDecimal num, String auctionID){
        BigDecimal i = bidRepository.buyOut(auctionID);
        return num.compareTo(i) >= 0;
    }

    public List<Bid> bidHistoryList(String userID){
        return bidRepository.bidHistory(userID);
    }

    private boolean auctionIsActive(String auctionID){
        return bidRepository.auctionIsActive(auctionID);
    }


}
