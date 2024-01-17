package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.receipt.service.ReceiptService;
import com.devcoop.kiosk.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
@RequiredArgsConstructor
public class ReceiptSaveController {
    private final ReceiptService receiptService;
    @PostMapping("/save/receipt")
    public void saveReceipt(@RequestBody KioskDto kioskDto) throws GlobalException {
        receiptService.saveReceipt(kioskDto);
    }
}
