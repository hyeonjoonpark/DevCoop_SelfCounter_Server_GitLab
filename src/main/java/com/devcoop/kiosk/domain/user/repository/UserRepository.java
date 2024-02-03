package com.devcoop.kiosk.domain.user.repository;

import com.devcoop.kiosk.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByCodeNumber(String codeNumber);

    boolean existsByCodeNumberAndPin(String codeNumber, String pin);

    User findPointByCodeNumber(String codeNumber);

    @Query("select u.pin " +
      "from User u " +
      "where u.codeNumber = :codeNumber and u.pin = :pin")
    String findPinByCodeNumberAndPin(@Param("codeNumber") String codenumber, @Param("pin") String pin);

    @Query("select u.studentNumber, u.codeNumber, u.studentName, u.point " +
      "from User u " +
      "where u.codeNumber = :codeNumber and u.pin = :pin")
  User findUserDetailByCodeNumberAndPin(@Param("codeNumber") String codenumber, @Param("pin") String pin);
}
