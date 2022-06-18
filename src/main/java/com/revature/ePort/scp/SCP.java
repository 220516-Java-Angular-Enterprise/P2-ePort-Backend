package com.revature.ePort.scp;

import com.revature.ePort.auctionshowing.AuctionShowing;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "SCP")
public class SCP {

    //todo look up how to create a blob object in java or how JPA handles blob objects

    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "img", nullable = false)
    private byte[] img;

    @OneToOne(mappedBy = "auction_showing")
    private AuctionShowing auctionShowing;
    @OneToMany(mappedBy = "scp")
    private List<SCPTag> scpTags;


    public SCP() {
        super();
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public List<SCPTag> getScpTags() {
        return scpTags;
    }

    public void setScpTags(List<SCPTag> scpTags) {
        this.scpTags = scpTags;
    }

    @Override
    public String toString() {
        return "SCP{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img=" + Arrays.toString(img) +
                ", scpTags=" + scpTags +
                '}';
    }
}
