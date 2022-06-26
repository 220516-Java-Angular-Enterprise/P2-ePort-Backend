package com.revature.ePort.tag;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.ePort.scp.SCP;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @JsonIgnore
    private String id;
    @Column(name = "tagName", nullable = false)
    private String tagName;

    @ManyToMany(mappedBy = "tag")
    @JsonIgnore
    private List<SCP> scp = new ArrayList<>();

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

    public List<SCP> getScp() {
        return scp;
    }

    public void setScp(List<SCP> scp) {
        this.scp = scp;
    }
}
