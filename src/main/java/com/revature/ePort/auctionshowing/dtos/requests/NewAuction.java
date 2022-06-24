package com.revature.ePort.auctionshowing.dtos.requests;

import java.sql.Timestamp;

public class NewAuction {
    private String title;
    private int buyOut=0;
    private int startingBid=0;
    private boolean status;
    private String startingDate;
    private String expiration;
    private String scp_id;
    private String user_id;


    public NewAuction() {

    }

    public NewAuction(int buyOut, int startingBid, String startingDate, String expiration, String title, String scp_id, String user_id) {
        this.buyOut = buyOut;
        this.startingBid = startingBid;
        this.startingDate = startingDate;
        this.expiration = expiration;
        this.title = title;
        this.scp_id = scp_id;
        this.user_id = user_id;
    }

    public int getBuyOut() {
        return buyOut;
    }

    public void setBuyOut(int buyOut) {
        this.buyOut = buyOut;
    }

    public int getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(int startingBid) {
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

    public String getScp_id() {
        return scp_id;
    }

    public void setScp_id(String scp_id) {
        this.scp_id = scp_id;
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
                ", scp='" + scp_id + '\'' +
                ", username='" + user_id + '\'' +
                '}';
    }
}
