package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Integer> {

}
