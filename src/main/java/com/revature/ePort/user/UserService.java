package com.revature.ePort.user;

import com.revature.ePort.user.dtos.requests.ActivateUser;
import com.revature.ePort.user.dtos.requests.EditUser;
import com.revature.ePort.user.dtos.requests.NewUserRequest;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import com.revature.ePort.util.custom_exception.ResourceConflictException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

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
        User user = request.extractUser();

        //validation checks
        String message = nullChecker(request);
        if(!message.isEmpty()) throw new InvalidRequestException(message);
        if(userExists(user.getUsername())) throw new ResourceConflictException("This username is already taken");
        if(!isValidUsername(user.getUsername())) throw new InvalidRequestException("Invalid username, must be 8-20 characters long and no special characters except _ and .");
        if(!isValidPassword(user.getPassword())) throw new InvalidRequestException("Invalid password, must be longer than 8 characters and contain one number, one special character, and one alphabetical character");
        //todo make this check have more info, or use the oauth from google
        if(!isValidEmail(user.getEmail())) throw new InvalidRequestException("Invalid email, must be a valid email address");

        userRepository.save(user);
        userRepository.encryptPassword(user.getPassword(),user.getId());
        return user;
    }

    public void enableUser(ActivateUser activateUser){
        if(activateUser.getUserID() == null) throw new InvalidRequestException("Invalid request, user ID is null");
        if(!userIDExists(activateUser.getUserID())) throw new InvalidRequestException("Invalid user ID");
        userRepository.updateUserStatus(activateUser.isActive(), activateUser.getUserID());
    }

    public void updateUser(EditUser editUser){
        User user = userRepository.getUserbyID(editUser.getUserID());

        if(user == null) throw new ResourceConflictException("Invalid user id");
        if(editUser.getUsername() != null && editUser.getUsername().equals(user.getUsername()) && userExists(user.getUsername())) throw new ResourceConflictException("This username is already taken");
        editUser.updateUser(user);
        if(!isValidUsername(user.getUsername())) throw new InvalidRequestException("Invalid username, must be 8-20 characters long and no special characters except _ and .");
        //todo make this check have more info, or use the oauth from google
        if(editUser.getEmail() != null && !isValidEmail(user.getEmail())) throw new InvalidRequestException("Invalid email, must be a valid email address");

        userRepository.updateUser(user.getUsername(),user.getCodename(),user.getEmail(),user.getPaymentID(),user.getShippingAddress(),user.getFunds(),editUser.getUserID());
        if(editUser.getPassword() != null && isValidPassword(editUser.getPassword())){
            userRepository.encryptPassword(editUser.getPassword(),editUser.getUserID());
        }else if(editUser.getPassword() != null)throw new InvalidRequestException("Invalid password, must be longer than 8 characters and contain one number, one special character, and one alphabetical character");
    }

    private String nullChecker(NewUserRequest request){
        String eMessage = "";
        //Checks if any fields are null and builds a message accordingly
        try {
            Map<String, String> nullFields = BeanUtils.describe(request);
            for (Map.Entry<String, String> key : nullFields.entrySet()) {
                if (key.getValue() == null) {
                    if(!eMessage.isEmpty()){
                        eMessage += ", ";
                    }
                    eMessage += key.getKey() + " is null";
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return eMessage;
    }

    private boolean userExists(String username){
        return userRepository.getAllUsername().contains(username);
    }

    private boolean isValidUsername(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isValidPassword(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    private boolean isValidEmail(String email){
        return email.matches("^([\\w][\\-\\_\\.]?)*\\w@([\\w+]\\-?)*\\w\\.\\w+$");
    }

    private boolean userIDExists(String userID){
        return userRepository.getAllUserID().contains(userID);
    }
}
