package com.revature.ePort.scp;

import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scp")
public class SCPController {

    @Inject
    private final SCPService scpService;

    @Inject
    @Autowired
    public SCPController(SCPService scpService){
        this.scpService = scpService;
    }


}
