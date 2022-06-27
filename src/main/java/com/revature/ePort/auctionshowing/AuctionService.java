package com.revature.ePort.auctionshowing;

import com.doomedcat17.scpier.ScpFoundationDataProvider;
import com.doomedcat17.scpier.data.scp.SCPBranch;
import com.doomedcat17.scpier.exception.SCPierApiException;
import com.revature.ePort.auctionshowing.dtos.requests.NewAuction;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import com.revature.ePort.user.User;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    @Inject
    private final AuctionRepository auctionRepository;

    @Inject
    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<AuctionShowing> getAllActive()  {
        return auctionRepository.activeAuctions();
    }

    public List<AuctionShowing> getAllUserAuctions(String userID){
        return auctionRepository.userAuctions(userID);
    }

    public void insertNewAuction(NewAuction newAuction){
        Timestamp startingDate = Timestamp.valueOf(newAuction.getStartingDate().replaceAll("[A-Z]", " "));
        Timestamp expiration = Timestamp.valueOf(newAuction.getExpiration().replaceAll("[A-Z]", " "));
        ///System.out.println(newAuction.getStartingDate());
        //newAuction.setStatus(!startingDate.after(Timestamp.valueOf(String.valueOf(System.currentTimeMillis()))));
        if(!validDates(startingDate,expiration)) throw new InvalidRequestException("Invalid start date");
        auctionRepository.newAuction(UUID.randomUUID().toString(), newAuction.getStatus(),newAuction.getBuyOut(), expiration, newAuction.getStartingBid()
                                    ,startingDate, newAuction.getTitle(),newAuction.getScp_id(),newAuction.getUser_id());
    }

    public List<AuctionShowing> sortAuctions(){
        List<AuctionShowing> sortedList = auctionRepository.findAll();
        sortedList.stream().sorted(Comparator.comparing(AuctionShowing::getStartingDate));
        return sortedList;
    }

    private boolean validDates(Timestamp start, Timestamp end){
        return start.before(end);
    }


}
