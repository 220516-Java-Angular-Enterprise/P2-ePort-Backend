package com.revature.ePort.bid.dtos.responses;

public class ViewBidHistory {

    private String title;
    private String auction_showing_id;
    private boolean auction_status;
    private double amount;

    public ViewBidHistory(String title, String auction_showing_id, boolean auction_status, double amount) {
        this.title = title;
        this.auction_showing_id = auction_showing_id;
        this.auction_status = auction_status;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ViewBidHistory{" +
                "title='" + title + '\'' +
                ", auction_showing_id='" + auction_showing_id + '\'' +
                ", auction_status=" + auction_status +
                ", amount=" + amount +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuction_showing_id() {
        return auction_showing_id;
    }

    public void setAuction_showing_id(String auction_showing_id) {
        this.auction_showing_id = auction_showing_id;
    }

    public boolean isAuction_status() {
        return auction_status;
    }

    public void setAuction_status(boolean auction_status) {
        this.auction_status = auction_status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
