package com.revature.ePort.user;

import com.revature.ePort.user.dtos.requests.NewUserRequest;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Inject
    private final UserRepository userRepository;

    @Inject
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Checks that the NewUserRequest fulfills the requirements to be
     * a new user and then adds it to the database
     * @param request A POJO containing the details to make a new user object and
     *               add it to the database
     * @return Returns the user object that was recently added to the database
     */
    public User register(NewUserRequest request){
        //todo implement this
        return null;
    }
}
