package com.revature.ePort.order.dtos.requests;

public class NewOrder {
    private String shipped_address;


    public NewOrder(String shipped_address) {
        this.shipped_address = shipped_address;
    }

    public String getShipped_address() {
        return shipped_address;
    }

    public void setShipped_address(String shipped_address) {
        this.shipped_address = shipped_address;
    }

    @Override
    public String toString() {
        return "NewOrder{" +
                "shipped_address='" + shipped_address + '\'' +
                '}';
    }
}
