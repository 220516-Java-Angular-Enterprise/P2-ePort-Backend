package com.revature.ePort.user.dtos.requests;

public class ActivateUser {

    private String userID;
    private boolean active = false;

    public ActivateUser() {
        super();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
