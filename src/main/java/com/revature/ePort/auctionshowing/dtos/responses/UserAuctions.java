package com.revature.ePort.auctionshowing.dtos.responses;

import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.bid.Bid;
import com.revature.ePort.schedule.closer.AuctionCloser;
import com.revature.ePort.scp.SCP;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserAuctions {
    private String id;
    private String title;
    private BigDecimal buyout_bid;
    private BigDecimal starting_bid;
    private Timestamp startDate;
    private Timestamp expirationDate;
    private BigDecimal highestBid;
    private SCP scp;

    private String scpName;

    private String scpImage;

    private String scpDescription;

    public UserAuctions(String title, BigDecimal buyout_bid, BigDecimal starting_bid, Timestamp startDate, Timestamp expirationDate, SCP scp, BigDecimal highestBid) {
        this.title = title;
        this.buyout_bid = buyout_bid;
        this.starting_bid = starting_bid;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.scp = scp;
        this.highestBid = highestBid;
    }

    public UserAuctions() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getBuyout_bid() {
        return buyout_bid;
    }

    public void setBuyout_bid(BigDecimal buyout_bid) {
        this.buyout_bid = buyout_bid;
    }

    public BigDecimal getStarting_bid() {
        return starting_bid;
    }

    public void setStarting_bid(BigDecimal starting_bid) {
        this.starting_bid = starting_bid;
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

    public SCP getScp() {
        return scp;
    }

    public void setScp(SCP scp) {
        this.scp = scp;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public String getScpName() {
        return scpName;
    }

    public void setScpName(String scpName) {
        this.scpName = scpName;
    }

    public String getScpImage() {
        return scpImage;
    }

    public void setScpImage(String scpImage) {
        this.scpImage = scpImage;
    }

    public String getScpDescription() {
        return scpDescription;
    }

    public void setScpDescription(String scpDescription) {
        this.scpDescription = scpDescription;
    }

    public void extractAuction(AuctionShowing details){
        id = details.getId();
        title = details.getTitle();
        buyout_bid = details.getBuyoutBid();
        starting_bid = details.getStartingBid();
        startDate = details.getStartingDate();
        expirationDate = details.getExpirationDate();
        details.getBids().stream().max(Comparator.comparing(Bid::getAmount)).ifPresent(bid -> highestBid = bid.getAmount());
        scp = details.getScp();
        scpName = scp.getName();
        scpImage = scp.getImg();
        scpDescription = scp.getDescription();
    }

    @Override
    public String toString() {
        return "UserAuctions{" +
                "title='" + title + '\'' +
                ", buyout_bid=" + buyout_bid +
                ", starting_bid=" + starting_bid +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                ", scp=" + scp +
                ", highestBid=" + highestBid +
                '}';
    }
}
