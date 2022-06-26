package com.revature.ePort.user.dtos.requests;

public class ActivateUser {

    private String id;
    private boolean isActive = false;

    public ActivateUser() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
