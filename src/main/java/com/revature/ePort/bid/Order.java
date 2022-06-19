package com.revature.ePort.bid;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
public class Order {

    //todo might want to move into its own package

    @Id
    private String id;
    @Column(name = "orderStatus", nullable = false)
    private String orderStatus;
    @Column(name = "orderSent", nullable = false)
    private Timestamp orderSent;
    @Column(name = "orderArrived")
    private Timestamp orderArrived;
    @Column(name = "shippedAddress", nullable = false)
    private String shippedAddress;

    @OneToOne(mappedBy = "order")
    private Bid bid;

    public Order() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderSent() {
        return orderSent;
    }

    public void setOrderSent(Timestamp orderSent) {
        this.orderSent = orderSent;
    }

    public Timestamp getOrderArrived() {
        return orderArrived;
    }

    public void setOrderArrived(Timestamp orderArrived) {
        this.orderArrived = orderArrived;
    }

    public String getShippedAddress() {
        return shippedAddress;
    }

    public void setShippedAddress(String shippedAddress) {
        this.shippedAddress = shippedAddress;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderSent=" + orderSent +
                ", orderArrived=" + orderArrived +
                ", shippedAddress='" + shippedAddress + '\'' +
                '}';
    }
}
