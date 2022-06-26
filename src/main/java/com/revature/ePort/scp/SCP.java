package com.revature.ePort.scp;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.ePort.auctionshowing.AuctionShowing;
import com.revature.ePort.tag.Tag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scp")
public class SCP {

    //todo set img to string because it will hold the img url instead

    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "img", nullable = false)
    private String img;

    @OneToOne(mappedBy = "scp")
    @JsonBackReference
    private AuctionShowing auctionShowing;

    /*@OneToMany(mappedBy = "scp")
    @JsonManagedReference*/
    @ManyToMany
    @JoinTable(
            name = "scp_tags",
            joinColumns = @JoinColumn(name = "scp_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tag = new ArrayList<>();


    public SCP() {
        super();
    }

    public SCP(String id, String name, String description, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public AuctionShowing getAuctionShowing() {
        return auctionShowing;
    }

    public void setAuctionShowing(AuctionShowing auctionShowing) {
        this.auctionShowing = auctionShowing;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }
}
