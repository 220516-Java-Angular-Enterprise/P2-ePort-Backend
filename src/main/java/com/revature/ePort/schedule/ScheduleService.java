package com.revature.ePort.schedule;

import com.revature.ePort.auctionshowing.AuctionService;
import com.revature.ePort.schedule.closer.AuctionCloser;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ScheduleService {

    @Inject
    private final AuctionCloser auctionCloser;
    private final AuctionService auctionService;

    private ScheduledExecutorService executor;

    public ScheduleService(AuctionCloser auctionCloser, AuctionService auctionService) {
        this.auctionCloser = auctionCloser;
        this.auctionService = auctionService;
    }

    public String createAuctionUpdater(){
        if(executor == null) {
            executor = Executors.newScheduledThreadPool(1);
            ScheduledFuture<?> result = executor.scheduleAtFixedRate(auctionCloser,1,1, TimeUnit.MINUTES);
            return "Auction Timer Started";
        }
        return "Auction Timer Already Started";
    }

    public String destroyAuctionUpdater(){
        if(executor != null) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
            executor = null;
            return "Auction Timer Ended";
        }
        return "Auction Timer Already Ended";
    }
}
