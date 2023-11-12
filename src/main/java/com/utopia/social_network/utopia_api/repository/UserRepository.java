/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trita
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{ 

    User findUserById(Long id);
    
    User findUserByUserNameAndPassword(String username, String password);
    
    User findUserByPhoneAndPassword (String phone, String password);

    User findUserByEmailAndPassword (String email, String password);
    
    @Query(value =  "SELECT * \n" +
                    "FROM USER\n" +
                    "WHERE ID != :id AND ID NOT IN \n" +
                    "(\n" +
                    "	SELECT F.USER_TARGET_ID\n" +
                    "	FROM USER U\n" +
                    "	INNER JOIN FOLLOWING F ON (U.ID = F.USER_SOURCE_ID)\n" +
                    "	WHERE U.ID = :id\n" +
                    "    UNION\n" +
                    "    SELECT RQ.USER_SOURCE_ID\n" +
                    "    FROM USER U2\n" +
                    "    INNER JOIN REQUEST_FOLLOW RQ ON (U2.ID = RQ.USER_TARGET_ID)\n" +
                    "    WHERE U2.ID = :id AND RQ.IS_PENDING = 1\n" +
                    ")", nativeQuery = true)
    List<User> getListSuggestFollow (@Param("id") Long id);
    
    @Modifying
    @Query(value =  "update user u set u.avatar_path = ? where u.id = ?", nativeQuery = true)
    void updateUserSetAvatarPathById(String path, Long id);
    
    @Modifying
    @Query(value =  "update user u "
                    + "set u.full_name = ?, "
                    + "u.website = ?, "
                    + "u.bio = ?, "
                    + "u.gender = ?"
                    + " where u.id = ?", nativeQuery = true)
    void updateUserSetAvatarPathById(String fullname, String website, String bio, String gender, Long id);

    User findUserByUserName(String userName);

    User findUserByEmail(String email);

    User findUserByPhone(String phone);
    
    List<User> findAllById(Long id);
}
