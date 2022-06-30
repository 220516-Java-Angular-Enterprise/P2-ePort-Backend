package com.revature.ePort.schedule;

import com.revature.ePort.auth.TokenService;
import com.revature.ePort.auth.dtos.response.Principal;
import com.revature.ePort.user.User;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Inject
    private final ScheduleService scheduleService;
    private final TokenService tokenService;

    @Inject
    @Autowired
    public ScheduleController(ScheduleService scheduleService, TokenService tokenService) {
        this.scheduleService = scheduleService;
        this.tokenService = tokenService;
    }


    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String start(@RequestHeader("Authorization") String token){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) throw new AuthenticationException("Admin only command");
        return scheduleService.createAuctionUpdater();
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/end", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String end(@RequestHeader("Authorization") String token){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) throw new AuthenticationException("Admin only command");
        return scheduleService.destroyAuctionUpdater();
    }
}
