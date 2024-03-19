package com.devcoop.kiosk.domain.receipt.presentation;

import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.receipt.service.ReceiptService;
import com.devcoop.kiosk.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SaveReceiptController {
    private final ReceiptService receiptService;
    @PostMapping("/save/receipt")
    public void saveReceipt(@RequestBody KioskRequest kioskRequest) throws GlobalException {
        receiptService.saveReceipt(kioskRequest);
    }
}
