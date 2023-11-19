package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class ReceiptSaveController {
    @PostMapping("/save/receipt")
    public KioskReceiptEntity saveReceipt(@RequestBody KioskDto kioskDto) {
        return null;
    }
}
