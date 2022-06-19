package com.revature.ePort.scp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SCPTagID implements Serializable {

    @Column(name = "scp_id", nullable = false)
    private String scpID;
    @Column(name = "tag_id", nullable = false)
    private String tagID;

    public SCPTagID(String scpID, String tagID) {
        this.scpID = scpID;
        this.tagID = tagID;
    }
}
