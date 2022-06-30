package com.revature.ePort.schedule.closer;

import com.revature.ePort.auctionshowing.AuctionService;
import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class AuctionCloser implements Runnable{

    @Inject
    private final AuctionService auctionService;
    private List<AuctionShowing> activeAuctionShowings;

    @Inject
    @Autowired
    public AuctionCloser(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public List<AuctionShowing> getActiveAuctionShowings() {
        return activeAuctionShowings;
    }

    public void setActiveAuctionShowings(List<AuctionShowing> activeAuctionShowings) {
        this.activeAuctionShowings = activeAuctionShowings;
    }

    @Override
    public void run() {
        try {
            System.out.println("Auction Update Check Started");
            activeAuctionShowings = auctionService.getAllActive();
            for (AuctionShowing auction: activeAuctionShowings) {
                System.out.println("Made it to the scheduled task");
                if (auction.getExpirationDate().before(Timestamp.valueOf(LocalDateTime.now()))){
                    auctionService.changeStatus(auction.getId());
                    //auctionService update auction here (closing auction)
                    //Create new order which sends out an email
                }

            }
            System.out.println("Finished Auction Check");
        } catch (Exception e){
            System.out.println("Error Scheduled update failed");
            e.printStackTrace();
        }
    }
}
