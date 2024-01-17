package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class ReceiptSaveController {
    @Autowired ReceiptService receiptService;
    @PostMapping("/save/receipt")
    public void saveReceipt(@RequestBody KioskDto kioskDto) {
        receiptService.saveReceipt(kioskDto);
    }
}
