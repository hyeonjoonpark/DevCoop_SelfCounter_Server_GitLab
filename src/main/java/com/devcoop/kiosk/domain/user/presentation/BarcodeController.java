package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.service.UserBarcodeService;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BarcodeController {
    private final UserBarcodeService userBarcodeService;

    @GetMapping("/userinfo")
    public Object getUserInfoByCodeNumber(@RequestParam String codeNumber) {
        try {
            return userBarcodeService.getUserInfoByCodeNumber(codeNumber);
        } catch(Exception | GlobalException e) {
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }
    }
}
