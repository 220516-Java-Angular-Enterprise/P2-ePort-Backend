package com.revature.ePort.bid.dtos.responses;

import java.math.BigDecimal;

public class ViewBidHistory {

    private String title;
    private String auction_showing_id;
    private boolean auction_status;
    private BigDecimal amount;

    public ViewBidHistory() {
    }

    public ViewBidHistory(String title, String auction_showing_id, boolean auction_status, BigDecimal amount) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
