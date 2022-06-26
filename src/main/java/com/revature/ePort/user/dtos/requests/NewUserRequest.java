package com.revature.ePort.user.dtos.requests;

import com.revature.ePort.user.User;

import java.math.BigDecimal;
import java.util.UUID;

public class NewUserRequest {

    private String username;
    private String password;
    private final String role = "DEFAULT";
    private String codename;
    private String paymentID;
    private String shippingAddress;
    private String email;

    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String username, String password, String codename, String paymentID, String shippingAddress, String email) {
        this.username = username;
        this.password = password;
        this.codename = codename;
        this.paymentID = paymentID;
        this.shippingAddress = shippingAddress;
        this.email = email;
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

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", codename='" + codename + '\'' +
                ", paymentID='" + paymentID + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User extractUser(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(password);
        user.setCodename(codename);
        user.setEmail(email);
        user.setPaymentID(paymentID);
        user.setShippingAddress(shippingAddress);
        user.setActive(false);
        user.setRole("DEFAULT");
        user.setFunds(BigDecimal.valueOf(0));
        return user;
    }
}
