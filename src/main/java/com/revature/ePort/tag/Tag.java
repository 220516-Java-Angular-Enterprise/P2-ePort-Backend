package com.revature.ePort.tag;

import com.revature.ePort.scp.SCPTag;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    private String id;
    @Column(name = "tagName", nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "scp")
    private List<SCPTag> scpTags;

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

    public List<SCPTag> getScpTags() {
        return scpTags;
    }

    public void setScpTags(List<SCPTag> scpTags) {
        this.scpTags = scpTags;
    }
}
