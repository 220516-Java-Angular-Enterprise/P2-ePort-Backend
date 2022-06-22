package com.revature.ePort.bid;

import com.revature.ePort.bid.dtos.requests.NewBid;
import com.revature.ePort.bid.dtos.responses.ViewBidHistory;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {

    @Inject
    private final BidService bidService;

    @Inject
    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    /*@PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    NewBid addNewBid(@RequestBody NewBid newBid){
        return bidService.addNewBid(newBid);
    }

    @PutMapping()
    @ResponseBody void updateBid(@RequestBody NewBid updateBid){
        bidService.updateBid(updateBid);
    }*/
}
