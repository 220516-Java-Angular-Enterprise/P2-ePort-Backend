package com.revature.ePort.scp;

import com.doomedcat17.scpier.data.content.ContentNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.revature.ePort.auth.TokenService;
import com.revature.ePort.auth.dtos.response.Principal;
import com.revature.ePort.scp.response.SCPOut;
import com.revature.ePort.user.User;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.AuthenticationException;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import com.revature.ePort.util.custom_exception.ResourceConflictException;
import com.revature.ePort.util.custom_exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scp")
public class SCPController {

    @Inject
    private final SCPService scpService;
    private final TokenService tokenService;

    @Inject
    @Autowired
    public SCPController(SCPService scpService, TokenService tokenService) {
        this.scpService = scpService;
        this.tokenService = tokenService;
    }





    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody SCP createSCP(@RequestHeader("Authorization") String token, @RequestBody SCPOut scpOut){
        Principal principal = tokenService.noTokenThrow(token);
        if(!(principal.getRole().equals("ADMIN")||principal.getRole().equals("SELLER"))) throw new AuthenticationException("Invalid authentication");
        return scpService.createSCP(scpOut.getName().toLowerCase());
    }


    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody SCP getSCP(@RequestHeader("Authorization") String token, @PathVariable String name){
        Principal principal = tokenService.noTokenThrow(token);
        if(!(principal.getRole().equals("ADMIN")||principal.getRole().equals("SELLER"))) throw new AuthenticationException("Invalid authentication");
        return scpService.findSCPByName(name);
    }

    //region Exception Handlers
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody Map<String, Object> handleUnauthorizedException(UnauthorizedException e){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", 401);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody Map<String, Object> handleAuthenticationException(AuthenticationException e){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", 403);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody Map<String, Object> handleInvalidRequestException(InvalidRequestException e){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", 404);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }

    /**
     * Catches any exceptions in other methods and returns status code 409 if
     * a ResourceConflictException occurs.
     * @param e The resource conflict request being thrown
     * @return A map containing the status code, error message, and timestamp of
     * when the error occurred.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody Map<String, Object> handleResourceConflictException(ResourceConflictException e){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", 409);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }
    //endregion
}
