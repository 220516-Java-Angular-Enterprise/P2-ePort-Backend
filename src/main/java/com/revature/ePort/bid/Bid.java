package com.revature.ePort.bid;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.order.Order;
import com.revature.ePort.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bid")
public class Bid {

    //todo find out how to do composite keys and make sure database works before pushing to main

    @EmbeddedId
    private BidID bidID;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "bidStatus", nullable = false)
    private boolean bidStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderID", referencedColumnName = "id")
    @JsonManagedReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "auction_showing_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private AuctionShowing auctionShowing;

    public Bid() {
        super();
    }

    public BidID getBidID() {
        return bidID;
    }

    public void setBidID(BidID bidID) {
        this.bidID = bidID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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