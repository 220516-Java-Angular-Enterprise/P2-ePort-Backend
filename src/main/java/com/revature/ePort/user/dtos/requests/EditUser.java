package com.revature.ePort.user.dtos.requests;

import com.revature.ePort.user.User;

public class EditUser {
    private String userID;
    private String username;
    private String password;
    private String codename;
    private String paymentID;
    private String shippingAddress;
    private String email;
    private int funds;

    public EditUser() {
        super();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String usedID) {
        this.userID = usedID;
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

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
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
        if(funds != 0)user.setFunds(user.getFunds() + funds);
        return user;
    }
}