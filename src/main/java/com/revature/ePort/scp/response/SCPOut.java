package com.revature.ePort.scp.response;

import javax.persistence.Column;

public class SCPOut {

    private String name;
    private String description;
    private String img;

    public SCPOut() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
