package com.revature.ePort.auctionshowing.dtos.responses;

import java.sql.Timestamp;

public class UserAuctions {
    private String title;
    private int buyout_bid;
    private int starting_bid;
    private Timestamp startDate;
    private Timestamp expirationDate;
    private int numberOfBids;

    public UserAuctions() {
    }

    public UserAuctions(int buyout_bid, int startingBid, Timestamp startDate, Timestamp expirationDate, String title, int numberOfBids) {
        this.buyout_bid = buyout_bid;
        this.starting_bid = startingBid;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.title = title;
        this.numberOfBids = numberOfBids;
    }

    public double getBuyout_bid() {
        return buyout_bid;
    }

    public void setBuyout_bid(int buyout_bid) {
        this.buyout_bid = buyout_bid;
    }

    public double getStartingBid() {
        return starting_bid;
    }

    public void setStartingBid(int startingBid) {
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

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
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
