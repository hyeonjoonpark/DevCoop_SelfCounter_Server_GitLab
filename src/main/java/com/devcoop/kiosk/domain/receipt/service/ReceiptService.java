package com.devcoop.kiosk.domain.receipt.service;

import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskDto;
import org.springframework.http.ResponseEntity;

public interface ReceiptService {
    ResponseEntity<Object> saveReceipt(KioskDto kioskDto);
}
