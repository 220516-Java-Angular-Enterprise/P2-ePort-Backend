package com.revature.ePort.auctionshowing.dtos.requests;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class NewAuction {
    private String title;
    private BigDecimal buyOut;
    private BigDecimal startingBid;
    private boolean status;
    private String startingDate;
    private String expiration;
    private String scpName;
    private String user_id;


    public NewAuction() {

    }

    public NewAuction(BigDecimal buyOut, BigDecimal startingBid, String startingDate, String expiration, String title, String scpName, String user_id) {
        this.buyOut = buyOut;
        this.startingBid = startingBid;
        this.startingDate = startingDate;
        this.expiration = expiration;
        this.title = title;
        this.scpName = scpName;
        this.user_id = user_id;
    }

    public BigDecimal getBuyOut() {
        return buyOut;
    }

    public void setBuyOut(BigDecimal buyOut) {
        this.buyOut = buyOut;
    }

    public BigDecimal getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(BigDecimal startingBid) {
        this.startingBid = startingBid;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getscpName() {
        return scpName;
    }

    public void setscpName(String scpName) {
        this.scpName = scpName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewAuction{" +
                "buyOut=" + buyOut +
                ", startingBid=" + startingBid +
                ", startingDate=" + startingDate +
                ", expiration=" + expiration +
                ", title='" + title + '\'' +
                ", scp='" + scpName + '\'' +
                ", username='" + user_id + '\'' +
                '}';
    }
}
