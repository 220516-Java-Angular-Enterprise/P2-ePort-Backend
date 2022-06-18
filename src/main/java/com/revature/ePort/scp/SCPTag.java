package com.revature.ePort.scp;

import javax.persistence.*;

@Entity
@Table(name = "scp_tags")
public class SCPTag {

    //todo might want to move into its own package and also put into a junction table because of many to many relationship


    @ManyToOne
    @JoinColumn(name = "scp_id", nullable = false)
    private SCP scp;

    public SCPTag() {
        super();
    }


}
