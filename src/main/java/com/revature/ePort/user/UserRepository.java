package com.revature.ePort.user;

import com.revature.ePort.util.specifications.UserSpecifications;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String>, JpaSpecificationExecutor<User> {


    @Modifying
    @Query(value = "Update users SET password = crypt(?1, gen_salt('bf')) WHERE id = ?2", nativeQuery = true)
    void encryptPassword(String password, String userID);

    @Modifying
    @Query(value = "UPDATE users SET is_active = ?1 WHERE id = ?2", nativeQuery = true)
    void updateUserStatus(boolean active, String userID);

    @Modifying
    @Query(value = "UPDATE users SET username = ?1, codename = ?2, email = ?3, paymentid = ?4, shipping_address = ?5, funds = ?6 WHERE id = ?7", nativeQuery = true)
    void updateUser(String username, String codename, String email, String paymentID, String shippingAddress, int funds, String id);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT username FROM users", nativeQuery = true)
    List<String> getAllUsername();

    @Query(value = "SELECT id FROM users", nativeQuery = true)
    List<String> getAllUserID();

    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    User getUserbyID(String id);

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User getUserByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = crypt(?2,password)", nativeQuery = true)
    User getUserByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(value = "DELETE FROM users WHERE username = ?1 AND is_active IS NOT TRUE", nativeQuery = true)
    int deleteUser(String username);

    List<User> findAll(Sort sort);

    @Query(value = "SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE table_schema = 'eport' AND TABLE_NAME = 'users'", nativeQuery = true)
    List<String> getColumnNames();

    /*@Query(value = "SELECT * FROM users u WHERE u.codename LIKE %:filter%", nativeQuery = true)
    List<User> getUserByFilter(@Param("filter") String filter);*/

    //List<User> getUserByFilter(Specification<User> userSpec);
}
