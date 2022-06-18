package com.revature.ePort.auth;

import com.revature.ePort.auth.dtos.requests.LoginRequest;
import com.revature.ePort.auth.dtos.response.Principal;
import com.revature.ePort.user.UserService;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Inject
    private final UserService userService;
    private final TokenService tokenService;

    @Inject
    @Autowired
    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Principal login(@RequestBody LoginRequest request, HttpServletResponse resp) {
        //todo implement this
        //Principal principal = new Principal(userService.login(request));
        //String token = tokenService.generateToken(principal);
        //resp.setHeader("Authorization", token);
        //return principal;
        return null;
    }
}
