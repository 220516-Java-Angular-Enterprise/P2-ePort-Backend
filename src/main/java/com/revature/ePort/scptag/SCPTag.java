package com.revature.ePort.scptag;

import com.revature.ePort.scp.SCP;
import com.revature.ePort.tag.Tag;

import javax.persistence.*;

@Entity
@Table(name = "scp_tags")
public class SCPTag {

    //todo might want to move into its own package and also put into a junction table because of many to many relationship

    @EmbeddedId
    private SCPTagID scpTagID;

    @ManyToOne
    @JoinColumn(name = "scp_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SCP scp;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Tag tag;

    public SCPTag() {
        super();
    }

    public SCPTagID getScpTagID() {
        return scpTagID;
    }

    public void setScpTagID(SCPTagID scpTagID) {
        this.scpTagID = scpTagID;
    }

    public SCP getScp() {
        return scp;
    }

    public void setScp(SCP scp) {
        this.scp = scp;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
