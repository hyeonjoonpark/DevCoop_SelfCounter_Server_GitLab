package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.service.UserBarcodeService;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kiosk")
@RequiredArgsConstructor
public class UserBarcodeController {
    private final UserBarcodeService userBarcodeService;

    @GetMapping("/userinfo")
    public Object getUserInfoByCodeNumber(@RequestParam String codeNumber) {
        try {
            return userBarcodeService.getUserInfoByCodeNumber(codeNumber);
        } catch(Exception e) {
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }
    }
}
