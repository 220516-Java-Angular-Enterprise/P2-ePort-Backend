package com.revature.ePort.auctionshowing;

import com.doomedcat17.scpier.ScpFoundationDataProvider;
import com.doomedcat17.scpier.data.scp.SCPBranch;
import com.doomedcat17.scpier.exception.SCPierApiException;
import com.revature.ePort.auctionshowing.dtos.requests.NewAuction;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import com.revature.ePort.user.User;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class AuctionService {
    @Inject
    private final AuctionRepository auctionRepository;

    @Inject
    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<ActiveAuctions> getAllActive()  {
        return auctionRepository.activeAuctions();
    }

    public List<UserAuctions> getAllUserAuctions(String userID){
        return auctionRepository.userAuctions(userID);
    }

    public void insertNewAuction(NewAuction newAuction){
        if(newAuction.getStartingDate().equals(null))
            newAuction.setStartingDate(new Timestamp(System.currentTimeMillis()));
        else newAuction.setStatus(!newAuction.getStartingDate().after(Timestamp.valueOf(String.valueOf(System.currentTimeMillis()))));

        auctionRepository.newAuction(UUID.randomUUID().toString(), newAuction.getStatus(),newAuction.getBuyOut(), newAuction.getExpiration(), newAuction.getStartingBid()
                                    ,newAuction.getStartingDate(), newAuction.getTitle(),newAuction.getScp_id(),newAuction.getUser_id());
    }
}
