package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import org.springframework.http.ResponseEntity;

public interface ReceiptService {
    ResponseEntity<Object> saveReceipt(KioskDto kioskDto);
}
