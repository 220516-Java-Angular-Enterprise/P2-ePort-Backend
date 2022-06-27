package com.revature.ePort.auctionshowing;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.ePort.bid.Bid;
import com.revature.ePort.scp.SCP;
import com.revature.ePort.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


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
    private BigDecimal startingBid;
    @Column(name = "buyoutBid", nullable = false)
    private BigDecimal buyoutBid;

    @Column(name = "number_of_bids", nullable = false)
    private BigDecimal numberOfBids;

    @OneToOne
    @JoinColumn(name = "SCP_ID", referencedColumnName = "id",nullable = false)
    @JsonManagedReference
    private SCP scp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "auctionShowing", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bid> bids;


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

    public BigDecimal getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(BigDecimal startingBid) {
        this.startingBid = startingBid;
    }

    public BigDecimal getBuyoutBid() {
        return buyoutBid;
    }

    public void setBuyoutBid(BigDecimal buyoutBid) {
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

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
