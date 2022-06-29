package com.revature.ePort.auctionshowing;

import com.revature.ePort.auctionshowing.dtos.requests.NewAuction;
import com.revature.ePort.auctionshowing.dtos.responses.ActiveAuctions;
import com.revature.ePort.auctionshowing.dtos.responses.UserAuctions;
import com.revature.ePort.auth.TokenService;
import com.revature.ePort.auth.dtos.response.Principal;
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
@RequestMapping("/auction")
public class AuctionController {

    @Inject
    private final AuctionService auctionService;
    private final TokenService tokenService;

    @Inject
    @Autowired
    public AuctionController(AuctionService auctionService, TokenService tokenService) {
        this.auctionService = auctionService;
        this.tokenService =tokenService;
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<AuctionShowing> activeAutions(@RequestHeader("Authorization") String token){
        Principal user = tokenService.noTokenThrow(token);
        return auctionService.getAllActive();
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value = "/history/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<AuctionShowing> userAuctions(@PathVariable String userid, @RequestHeader("Authorization") String token){
        Principal user = tokenService.noTokenThrow(token);
        if(!user.getRole().equals("ADMIN") && !user.getRole().equals("SELLER")) throw new InvalidRequestException("Unauthorized");
        return auctionService.getAllUserAuctions(userid);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/newAuction", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    NewAuction insertAuction(@RequestBody NewAuction newAuction, @RequestHeader("Authorization") String token){
        Principal user = tokenService.noTokenThrow(token);
        if(!user.getRole().equals("ADMIN") && !user.getRole().equals("SELLER")) throw new InvalidRequestException("Unauthorized");
        auctionService.insertNewAuction(newAuction);
        return newAuction;
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuctionShowing> sortedAuctions(@RequestHeader("Authorization") String token){
        Principal user = tokenService.noTokenThrow(token);
        return auctionService.sortAuctions();
    }

    //region Exception Handlers
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    Map<String, Object> handleUnauthorizedException(UnauthorizedException e){
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


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody Map<String, Object> handleResourceConflictException(ResourceConflictException e){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", 409);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }
}
