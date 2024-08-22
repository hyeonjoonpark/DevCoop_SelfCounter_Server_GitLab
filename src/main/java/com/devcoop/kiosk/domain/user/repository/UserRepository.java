package com.devcoop.kiosk.domain.user.repository;

import com.devcoop.kiosk.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserCode(String userCode);

    @Query("select u.userPin " +
           "from User u " +
           "where u.userCode = :userCode and u.userPin = :userPin")
    String findPinByUserCodeAndUserPin(@Param("userCode") String userCode, @Param("userPin") String userPin);

    @Query("select u.userNumber, u.userCode, u.userName, u.userPoint " +
           "from User u " +
           "where u.userCode = :userCode and u.userPin = :userPin")
    User findUserDetailByUserCodeAndUserPin(@Param("userCode") String userCode, @Param("userPin") String userPin);

    boolean existsByUserCode(String userCode);

    User findByUserName(String userName);
    User findByUserEmail(String userEmail);
}
