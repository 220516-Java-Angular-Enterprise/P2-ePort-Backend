package com.revature.ePort.bid.dtos.requests;

public class NewBid {

    private String auction_id;
    private String user_id;
    private int amount;

    public NewBid() {
    }

    public NewBid(String auction_id, String user_id, int amount) {
        this.auction_id = auction_id;
        this.user_id = user_id;
        this.amount = amount;
    }

    public String getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(String auction_id) {
        this.auction_id = auction_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "NewBid{" +
                "auction_id='" + auction_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
