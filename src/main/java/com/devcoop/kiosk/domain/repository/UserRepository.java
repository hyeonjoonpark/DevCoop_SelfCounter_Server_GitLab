package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByCodeNumber(String codeNumber);

    boolean existsByCodeNumberAndPin(String codeNumber, String pin);
}
