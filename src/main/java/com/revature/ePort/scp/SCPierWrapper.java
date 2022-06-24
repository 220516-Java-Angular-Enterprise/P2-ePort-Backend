package com.revature.ePort.scp;

import com.doomedcat17.scpier.ScpFoundationDataProvider;
import com.doomedcat17.scpier.data.scp.SCPBranch;
import com.doomedcat17.scpier.data.scp.ScpWikiData;
import com.doomedcat17.scpier.exception.SCPierApiException;
import org.springframework.stereotype.Component;

@Component
public class SCPierWrapper {

    public SCPierWrapper() {
        super();
    }

    public synchronized ScpWikiData getScpWikiData(String name) throws SCPierApiException {
        ScpFoundationDataProvider scpFoundationDataProvider = new ScpFoundationDataProvider();
        return scpFoundationDataProvider.getScpWikiData(name, SCPBranch.ENGLISH);
        //might need to change the out put into something
    }
}
