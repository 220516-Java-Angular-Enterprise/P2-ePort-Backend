package com.revature.ePort.auctionshowing;

import com.doomedcat17.scpier.ScpFoundationDataProvider;
import com.doomedcat17.scpier.data.scp.SCPBranch;
import com.doomedcat17.scpier.exception.SCPierApiException;
import com.revature.ePort.auctionshowing.dtos.requests.NewAuction;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import com.revature.ePort.scp.SCP;
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

    public List<AuctionShowing> getExpiredAuctions(){
        return auctionRepository.expiredAuctions();
    }

    public List<AuctionShowing> getAllUserAuctions(String userID){
        return auctionRepository.userAuctions(userID);
    }

    public void insertNewAuction(NewAuction newAuction){
        Timestamp startingDate = Timestamp.valueOf(newAuction.getStartingDate().replaceAll("[A-Z]", " "));
        Timestamp expiration = Timestamp.valueOf(newAuction.getExpiration().replaceAll("[A-Z]", " "));
        //setting the scp name to scp ID to insert into the database
        newAuction.setscpName(auctionRepository.returnScpID(newAuction.getscpName()));
        if(!scpDuplicate(newAuction.getscpName())) throw new InvalidRequestException("Duplicate SCP");
        if(!validDates(startingDate,expiration)) throw new InvalidRequestException("Invalid start date");
        if(!titleDuplicate(newAuction.getTitle())) throw new InvalidRequestException("Duplicate title");
        auctionRepository.newAuction(UUID.randomUUID().toString(), newAuction.getStatus(),newAuction.getBuyOut(), expiration, newAuction.getStartingBid()
                                    ,startingDate, newAuction.getTitle(),newAuction.getscpName(),newAuction.getUser_id());
    }

    public UserAuctions detailedAuction(String title){
        UserAuctions details = new UserAuctions();
        details.extractAuction(auctionRepository.auction(title));
        return details;
    }

    public List<AuctionShowing> sortAuctions(){
        List<AuctionShowing> sortedList = auctionRepository.activeAuctions();
        sortedList.stream().sorted(Comparator.comparing(AuctionShowing::getStartingDate));
        return sortedList;
    }

    public void changeStatus (String id){
        if(auctionRepository.auctionStatus(id)) auctionRepository.disableAuction(id);
        else auctionRepository.enableAuction(id);
    }

    private boolean titleDuplicate(String title){
        return auctionRepository.titleDuplicate(title) == null;
    }

    private boolean validDates(Timestamp start, Timestamp end){
        return start.before(end);
    }

    private boolean scpDuplicate(String scpID){
        return auctionRepository.duplicateAuction(scpID) == null;
    }


}
