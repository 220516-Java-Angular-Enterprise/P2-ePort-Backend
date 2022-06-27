package com.revature.eport;

import com.revature.ePort.user.UserRepository;
import com.revature.ePort.user.UserService;
import com.revature.ePort.user.dtos.requests.NewUserRequest;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import com.revature.ePort.util.custom_exception.ResourceConflictException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class UserTesting {

    private UserService userService;
    private UserRepository userRepository;
    private NewUserRequest newUserRequest;

    @Before
    public void setup(){
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void emptyUsername() {
        newUserRequest = new NewUserRequest("", "password", "codename", "1", "123 abc", "User@gmail.com");
        Assert.assertThrows(InvalidRequestException.class, () -> userService.register(newUserRequest));
    }

    @Test
    public void duplicateUsername() {
        List<String> usernames = new ArrayList<>();
        usernames.add("auctionUser002");
        when(userRepository.getAllUsername()).thenReturn(usernames);
        newUserRequest = new NewUserRequest("auctionUser002", "P@ssw0rd", "codename", "1", "123 abc", "User@gmail.com");
        Assert.assertThrows(ResourceConflictException.class, () -> userService.register(newUserRequest));
    }

    @Test
    public void invalidPassword() {
        newUserRequest = new NewUserRequest("auctionUser003", "password", "codename", "1", "123 abc", "User@gmail.com");
        Assert.assertThrows(InvalidRequestException.class, () -> userService.register(newUserRequest));
    }

    @Test
    public void invalidEmail(){
        newUserRequest = new NewUserRequest("auctionUser003", "P@ssw0rd", "codename", "1", "123 abc", "User.com");
        Assert.assertThrows(InvalidRequestException.class, () -> userService.register(newUserRequest));
    }


}
