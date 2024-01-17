package com.devcoop.kiosk.domain.user.repository;

import com.devcoop.kiosk.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByCodeNumber(String codeNumber);

    boolean existsByCodeNumberAndPin(String codeNumber, String pin);

    User findPointByCodeNumber(String codeNumber);
}
