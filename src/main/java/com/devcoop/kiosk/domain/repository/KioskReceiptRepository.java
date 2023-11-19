package com.devcoop.kiosk.domain.repository;

import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KioskReceiptRepository extends JpaRepository<KioskReceiptEntity, Integer> {

}
