package com.revature.ePort.user;

import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.bid.Bid;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "codename", nullable = false)
    private String codename;
    @Column(name = "shippingAddress", nullable = false)
    private String shippingAddress;
    @Column(name = "funds", nullable = false)
    private int funds;
    @Column(name = "paymentID")
    private String paymentID;
    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private List<Bid> bids;

    @OneToMany(mappedBy = "user")
    private List<AuctionShowing> auctionShowings;


    public User() {
        super();
    }

    public User(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public List<AuctionShowing> getAuctionShowings() {
        return auctionShowings;
    }

    public void setAuctionShowings(List<AuctionShowing> auctionShowings) {
        this.auctionShowings = auctionShowings;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", codename='" + codename + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", funds=" + funds +
                ", paymentID='" + paymentID + '\'' +
                ", isActive=" + isActive +
                ", bids=" + bids +
                ", auctionShowings=" + auctionShowings +
                '}';
    }
}
