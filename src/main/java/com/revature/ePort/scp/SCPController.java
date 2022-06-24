package com.revature.ePort.scp;

import com.doomedcat17.scpier.data.content.ContentNode;
import com.revature.ePort.scp.response.SCPOut;
import com.revature.ePort.user.User;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createSCP(@RequestBody SCPOut scpOut){
        return scpService.createSCP(scpOut.getName());
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody SCP getSCP(@PathVariable String name){
        return scpService.findSCPByName(name);
    }
}
