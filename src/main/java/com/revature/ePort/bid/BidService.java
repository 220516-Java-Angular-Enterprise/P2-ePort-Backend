package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BidService {
    @Inject
    private final BidRepository bidRepository;

    @Inject
    @Autowired
    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public NewBid addNewBid(NewBid newBid){
        return bidRepository.addNewBid(newBid.getAuction_id(), newBid.getUser_id(),newBid.getAmount());
    }

    public void updateBid(NewBid updateBid){
        bidRepository.updateBid(updateBid.getAmount(),updateBid.getUser_id(),updateBid.getAuction_id());
    }
}
