package com.devcoop.kiosk.domain.receipt.service;

import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskDto;
import com.devcoop.kiosk.global.exception.GlobalException;
import org.springframework.http.ResponseEntity;

public interface ReceiptService {
    ResponseEntity<Object> saveReceipt(KioskDto kioskDto) throws GlobalException;
}
