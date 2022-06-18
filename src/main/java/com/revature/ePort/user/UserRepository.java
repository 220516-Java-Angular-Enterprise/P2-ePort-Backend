package com.revature.ePort.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    /*Example code for adding a new user to the database*/
    @Modifying
    @Query(value = "INSERT INTO users (id, username, password, role) VALUES (?1, ?2, crypt(?3, gen_salt('bf')), ?4)", nativeQuery = true)
    void saveUser(String id, String username, String password, String role);
}
