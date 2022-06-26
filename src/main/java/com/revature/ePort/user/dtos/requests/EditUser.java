package com.revature.ePort.user.dtos.requests;

import com.revature.ePort.user.User;

import java.math.BigDecimal;


public class EditUser {
    private String id;
    private String username;
    private String password;
    private String codename;
    private String paymentID;
    private String shippingAddress;
    private String email;
    private BigDecimal funds;

    public EditUser() {
        super();
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

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public User updateUser(User user){
        //todo get password working
        if(username != null)user.setUsername(username);
        //if(password != null)user.setPassword(password);
        if(codename != null)user.setCodename(codename);
        if(email != null)user.setEmail(email);
        if(paymentID != null)user.setPaymentID(paymentID);
        if(shippingAddress != null)user.setShippingAddress(shippingAddress);
        if(funds.equals(0))user.setFunds(user.getFunds().add(funds));
        return user;
    }
}
