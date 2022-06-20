package com.revature.ePort.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {


    @Modifying
    @Query(value = "Update users SET password = crypt(?1, gen_salt('bf')) WHERE id = ?2", nativeQuery = true)
    void encryptPassword(String password, String userID);

    @Modifying
    @Query(value = "UPDATE users SET is_active = ?1 WHERE id = ?2", nativeQuery = true)
    void updateUserStatus(boolean active, String userID);

    @Modifying
    @Query(value = "UPDATE users SET username = ?1, codename = ?2, email = ?3, paymentid = ?4, shipping_address = ?5, funds = ?6 WHERE id = ?7", nativeQuery = true)
    void updateUser(String username, String codename, String email, String paymentID, String shippingAddress, int funds, String id);

    @Query(value = "SELECT username FROM users", nativeQuery = true)
    List<String> getAllUsername();

    @Query(value = "SELECT id FROM users", nativeQuery = true)
    List<String> getAllUserID();

    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    User getUserbyID(String id);
}
