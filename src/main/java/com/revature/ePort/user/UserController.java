package com.revature.ePort.user;

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
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
    public @ResponseBody String register(@RequestBody NewUserRequest newUserRequest){
        return userService.register(newUserRequest).getId();
    }

    @CrossOrigin
    @RequestMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User getUser(@RequestBody NewUserRequest newUserRequest){
        return userService.getUserByUsername(newUserRequest.getUsername());
    }
    //endregion

    //region HTTP Put methods
    @CrossOrigin
    @RequestMapping("/activate")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public void activate(@RequestBody ActivateUser activateUser){
        userService.enableUser(activateUser);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public void editDetails(@RequestBody EditUser editUser){
        userService.updateUser(editUser);
    }
    //endregion

    //region HTTP Delete methods
    @CrossOrigin
    @RequestMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteInactiveUser(@RequestBody ActivateUser activateUser){
        userService.deleteUser(activateUser);
    }
    //endregion

    //region HTTP Get methods
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    //endregion

    //region Exception Handlers
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
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
