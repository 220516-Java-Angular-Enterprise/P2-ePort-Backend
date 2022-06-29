package com.revature.ePort.user;

import com.revature.ePort.auth.TokenService;
import com.revature.ePort.auth.dtos.response.Principal;
import com.revature.ePort.user.dtos.requests.ActivateUser;
import com.revature.ePort.user.dtos.requests.EditUser;
import com.revature.ePort.user.dtos.requests.NewUserRequest;
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
@RequestMapping("/users")
public class UserController {

    @Inject
    private final UserService userService;
    @Inject
    private final TokenService tokenService;

    @Inject
    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }



    //region HTTP Post methods
    /**
     * Takes in a new user request in the form of a mapped json object
     * and enters it into the database.
     * @param newUserRequest A json object mapped to the pojo NewUserRequest
     * @return The ID of the newly created user.
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody NewUserRequest newUserRequest){
        userService.register(newUserRequest).getId();
    }

    //endregion

    //region HTTP Put methods
    @CrossOrigin
    //@RequestMapping("/activate") //does not work set value in the HTTP verb mapping
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/activate", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public void activate(@RequestHeader("Authorization") String token, @RequestBody ActivateUser activateUser){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) throw new AuthenticationException("Invalid authorization");
        userService.enableUser(activateUser);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public void editDetails(@RequestHeader("Authorization") String token, @RequestBody EditUser editUser){
        Principal principal = tokenService.noTokenThrow(token);
        editUser.setId(principal.getId());
        userService.updateUser(editUser);
    }
    //endregion

    //region HTTP Delete methods
    @CrossOrigin()
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteInactiveUser(@RequestHeader("Authorization") String token, @PathVariable String username){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) throw new AuthenticationException("Invalid authorization");
        userService.deleteUser(username);
    }
    //endregion

    //region HTTP Get methods
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User getUser(@RequestHeader("Authorization") String token, @PathVariable String username){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) return userService.getUserByUsername(principal.getUsername());
        else return userService.getUserByUsername(username);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{sort}/{columnName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAllUsersSorted(@RequestHeader("Authorization") String token, @PathVariable String sort,@PathVariable String columnName){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) throw new AuthenticationException("Invalid authorization");
        return userService.sortUsers(sort, columnName);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/filter/{columnName}/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAllUsersFiltered(@RequestHeader("Authorization") String token, @PathVariable String columnName,@PathVariable String filter){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) throw new AuthenticationException("Invalid authorization");
        return userService.filterUser(columnName, filter);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAllUsers(@RequestHeader("Authorization") String token){
        Principal principal = tokenService.noTokenThrow(token);
        if(!principal.getRole().equals("ADMIN")) throw new AuthenticationException("Invalid authorization");
        return userService.getAllUsers();
    }
    //endregion

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

    /*private Principal noTokenThrow(String token){
        Principal requester = tokenService.extractRequesterDetails(token);
        if(requester == null) throw new UnauthorizedException("No authorization found");//401
        User user = userService.getUserByUsername(requester.getUsername());
        if(user == null) throw new InvalidRequestException("Error cannot find user");//404
        if(!user.isActive()) throw new AuthenticationException("Inactive user");//403
        return requester;
    }*/

}
