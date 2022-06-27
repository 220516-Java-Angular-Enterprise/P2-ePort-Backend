package com.revature.ePort.auctionshowing.dtos.responses;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserAuctions {
    private String title;
    private BigDecimal buyout_bid;
    private BigDecimal starting_bid;
    private Timestamp startDate;
    private Timestamp expirationDate;
    private BigDecimal numberOfBids;

    public UserAuctions() {
    }

    public UserAuctions(BigDecimal buyout_bid, BigDecimal startingBid, Timestamp startDate, Timestamp expirationDate, String title, BigDecimal numberOfBids) {
        this.buyout_bid = buyout_bid;
        this.starting_bid = startingBid;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.title = title;
        this.numberOfBids = numberOfBids;
    }

    public BigDecimal getBuyout_bid() {
        return buyout_bid;
    }

    public void setBuyout_bid(BigDecimal buyout_bid) {
        this.buyout_bid = buyout_bid;
    }

    public BigDecimal getStartingBid() {
        return starting_bid;
    }

    public void setStartingBid(BigDecimal startingBid) {
        this.starting_bid = startingBid;
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

    public BigDecimal getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(BigDecimal numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    @Override
    public String toString() {
        return "UserAuctions{" +
                "buyout_bid=" + buyout_bid +
                ", startingBid=" + starting_bid +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                ", title='" + title + '\'' +
                ", numberOfBids=" + numberOfBids +
                '}';
    }
}
