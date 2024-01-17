package com.devcoop.kiosk.domain.paylog.repository;

import com.devcoop.kiosk.domain.paylog.PayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayLogRepository extends JpaRepository<PayLog, Integer> {

}
