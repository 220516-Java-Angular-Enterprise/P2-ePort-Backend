package com.revature.ePort.bid;

import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.user.User;

import javax.persistence.*;

@Entity
@Table(name = "bid")
public class Bid {

    //todo find out how to do composite keys and make sure database works before pushing to main

    private String auctionShowingID;

    private String userID;

    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "bidStatus", nullable = false)
    private boolean bidStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderID", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "User_ID", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Auction_Showing_ID", referencedColumnName = "id")
    private AuctionShowing auctionShowing;

    public Bid() {
        super();
    }

    public String getAuctionShowingID() {
        return auctionShowingID;
    }

    public void setAuctionShowingID(String auctionShowingID) {
        this.auctionShowingID = auctionShowingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(boolean bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuctionShowing getAuctionShowing() {
        return auctionShowing;
    }

    public void setAuctionShowing(AuctionShowing auctionShowing) {
        this.auctionShowing = auctionShowing;
    }
}
