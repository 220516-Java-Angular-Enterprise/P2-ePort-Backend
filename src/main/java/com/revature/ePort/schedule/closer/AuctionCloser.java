package com.revature.ePort.schedule.closer;

import com.revature.ePort.auctionshowing.AuctionService;
import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.bid.Bid;
import com.revature.ePort.bid.BidID;
import com.revature.ePort.bid.BidService;

import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class AuctionCloser implements Runnable{

    @Inject
    private final BidService bidService;
    private final AuctionService auctionService;

    @Inject
    @Autowired
    public AuctionCloser(BidService bidService, AuctionService auctionService) {
        this.bidService = bidService;
        this.auctionService = auctionService;
    }


    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            for (AuctionShowing auction:auctionService.getExpiredAuctions()) {
                auctionService.changeStatus(auction.getId());
                List<Bid> bids = bidService.getFinalBids(auction.getId());
                String auctionID = "";
                if(!bids.isEmpty()){
                    Bid bid = bids.get(0);
                    auctionID = bid.getBidID().getAuctionShowingID();
                    //auctionService.changeStatus(auctionID);
                    String userID = bid.getBidID().getUserID();
                    bidService.closeBid(auctionID, userID);
                }

            }
            long finish = System.currentTimeMillis();
            System.out.println("Time taken: " + (finish - start) + "ms");
        } catch (Exception e){
            System.out.println("Error Scheduled update failed");
            e.printStackTrace();
        }
    }
}
