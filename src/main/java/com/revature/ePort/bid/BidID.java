package com.revature.ePort.bid;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BidID implements Serializable {

    @Column(name = "auction_showing_id", nullable = false)
    private String auctionShowingID;
    @Column(name = "user_id", nullable = false)
    private String userID;

    public BidID() {
        super();
    }

    public BidID(String auctionShowingID, String userID) {
        this.auctionShowingID = auctionShowingID;
        this.userID = userID;
    }
}