package com.revature.ePort.scpier;

import com.doomedcat17.scpier.ScpFoundationDataProvider;
import com.doomedcat17.scpier.data.scp.SCPBranch;
import com.doomedcat17.scpier.data.scp.ScpWikiData;
import com.doomedcat17.scpier.exception.SCPierApiException;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import org.springframework.stereotype.Component;

@Component
public class SCPierWrapper {

    public SCPierWrapper() {
        super();
    }

    public synchronized ScpWikiData getScpWikiData(String name) throws SCPierApiException {//SCPier is not thread safe
        try {
            ScpFoundationDataProvider scpFoundationDataProvider = new ScpFoundationDataProvider();
            return scpFoundationDataProvider.getScpWikiData(name, SCPBranch.ENGLISH);
        } catch (Exception e){
            throw new InvalidRequestException("Unable to find SCP");
        }
        //might need to change the out put into something
    }
}
