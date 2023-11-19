package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.PayLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayLogRepository extends JpaRepository<PayLogEntity, Integer> {

}
