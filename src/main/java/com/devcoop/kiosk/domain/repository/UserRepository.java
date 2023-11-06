package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
