package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.bid.dtos.responses.ViewBidHistory;
import com.revature.ePort.user.User;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


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
        bidRepository.addNewBid(newBid.getAuction_id(), newBid.getUser_id(),newBid.getAmount());
    }

    public void updateBid(NewBid updateBid){
        if(!correctPricing(updateBid.getAmount(),updateBid.getAuction_id())) throw new ResourceConflictException("Amount not valid");
        if(!bidExists(updateBid.getAuction_id(),updateBid.getUser_id())) throw new ResourceConflictException("Bid doesn't exist");
        bidRepository.updateBid(updateBid.getAmount(),updateBid.getUser_id(),updateBid.getAuction_id());
    }

    private boolean bidExists(String auctionID, String userID){
        return bidRepository.auctionExists(auctionID, userID) != null;
    }

    private boolean correctPricing(BigDecimal num, String auctionID){
        BigDecimal i = bidRepository.maxAmount(auctionID);
        return num.compareTo(i) > 0;
    }

    public List<Bid> bidHistoryList(String userID){
        return bidRepository.bidHistory(userID);
    }
}
