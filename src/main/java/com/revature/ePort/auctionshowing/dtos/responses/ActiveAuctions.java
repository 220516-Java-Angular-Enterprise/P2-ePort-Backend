package com.revature.ePort.auctionshowing.dtos.responses;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ActiveAuctions {
    private String username;
    private BigDecimal buyout_bid;
    private BigDecimal startingBid;
    private Timestamp startDate;
    private Timestamp expirationDate;
    private String title;

    public ActiveAuctions(){
        super();
    }

    public ActiveAuctions(String username, BigDecimal buyout_bid, BigDecimal startingBid, Timestamp startDate, Timestamp expirationDate, String title) {
        this.username = username;
        this.buyout_bid = buyout_bid;
        this.startingBid = startingBid;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBuyout_bid() {
        return buyout_bid;
    }

    public void setBuyout_bid(BigDecimal buyout_bid) {
        this.buyout_bid = buyout_bid;
    }

    public BigDecimal getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(BigDecimal startingBid) {
        this.startingBid = startingBid;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ActiveAuctions{" +
                "username='" + username + '\'' +
                ", buyout_bid=" + buyout_bid +
                ", startingBid=" + startingBid +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                ", title='" + title + '\'' +
                '}';
    }
}
