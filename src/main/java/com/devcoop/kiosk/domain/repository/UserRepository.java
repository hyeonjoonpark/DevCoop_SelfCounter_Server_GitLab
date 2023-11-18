package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByCodeNumber(String codeNumber);

    boolean existsByCodeNumberAndPin(String codeNumber, String pin);

    UserEntity findPointByCodeNumber(String codeNumber);
}
