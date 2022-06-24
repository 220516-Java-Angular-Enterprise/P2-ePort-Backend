package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.bid.dtos.responses.ViewBidHistory;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        bidRepository.updateBid(updateBid.getAmount(),updateBid.getUser_id(),updateBid.getAuction_id());
    }

    public List<Bid> bidHistoryList(String userID){
        return bidRepository.bidHistory(userID);
    }
}
