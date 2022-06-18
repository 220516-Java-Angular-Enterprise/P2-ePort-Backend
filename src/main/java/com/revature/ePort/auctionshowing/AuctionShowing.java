package com.revature.ePort.auctionshowing;

import com.revature.ePort.scp.SCP;
import com.revature.ePort.user.User;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "auction_showing")
public class AuctionShowing {

    @Id
    private String id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "auctionStatus", nullable = false)
    private boolean auctionStatus;
    @Column(name = "startingDate", nullable = false)
    private Timestamp startingDate;
    @Column(name = "expirationDate", nullable = false)
    private Timestamp expirationDate;
    @Column(name = "startingBid", nullable = false)
    private int startingBid;
    @Column(name = "buyoutBid", nullable = false)
    private int buyoutBid;
    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "SCP_ID", referencedColumnName = "id",nullable = false)
    private SCP scp;


    public AuctionShowing() {
        super();
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

    public boolean isAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(boolean auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Timestamp getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Timestamp startingDate) {
        this.startingDate = startingDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(int startingBid) {
        this.startingBid = startingBid;
    }

    public int getBuyoutBid() {
        return buyoutBid;
    }

    public void setBuyoutBid(int buyoutBid) {
        this.buyoutBid = buyoutBid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SCP getScp() {
        return scp;
    }

    public void setScp(SCP scp) {
        this.scp = scp;
    }

    @Override
    public String toString() {
        return "AuctionShowing{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", auctionStatus=" + auctionStatus +
                ", startingDate=" + startingDate +
                ", expirationDate=" + expirationDate +
                ", startingBid=" + startingBid +
                ", buyoutBid=" + buyoutBid +
                ", user=" + user +
                ", scp=" + scp +
                '}';
    }
}
