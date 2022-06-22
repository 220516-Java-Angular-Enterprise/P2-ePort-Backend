package com.revature.ePort.scp;

import com.doomedcat17.scpier.data.content.ContentNode;
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String  getSCPByName(){
        //todo make this method using uri parameters for input
        return "WIP";
    }
    @CrossOrigin
    @RequestMapping("/description")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String  getSCPDescription(){
        return scpService.findSCPDescription();
    }

    @CrossOrigin
    @RequestMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<String>  getSCPImage(){
        return scpService.findSCPImage();
    }

    @CrossOrigin
    @RequestMapping("/content")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ContentNode<?>> getSCPContent(){
        return scpService.findSCPContent();
    }
}
