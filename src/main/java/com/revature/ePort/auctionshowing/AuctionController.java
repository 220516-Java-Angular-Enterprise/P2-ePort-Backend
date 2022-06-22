package com.revature.ePort.auctionshowing;

import com.revature.ePort.auctionshowing.dtos.requests.NewAuction;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Inject
    private final AuctionService auctionService;

    @Inject
    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<ActiveAuctions> activeAutions(){return auctionService.getAllActive();}

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/userActive", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<UserAuctions> userAuctions(@RequestBody String userid){
        return auctionService.getAllUserAuctions(userid);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/newAuction", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    NewAuction insertAuction(@RequestBody NewAuction newAuction){
        auctionService.insertNewAuction(newAuction);
        return newAuction;
    }
}
