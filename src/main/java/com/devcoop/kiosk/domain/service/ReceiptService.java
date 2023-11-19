package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.presentation.dto.PayLogRequestDto;

public interface ReceiptService {
    void saveReceipt(KioskDto kioskDto);
}
