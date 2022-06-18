package com.revature.ePort.scp;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    private String id;
    @Column(name = "tagName", nullable = false)
    private String tagName;

    public Tag() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
