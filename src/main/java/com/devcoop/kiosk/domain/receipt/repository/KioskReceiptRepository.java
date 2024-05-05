package com.devcoop.kiosk.domain.receipt.repository;

import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KioskReceiptRepository extends JpaRepository<KioskReceipt, Integer> {

}


