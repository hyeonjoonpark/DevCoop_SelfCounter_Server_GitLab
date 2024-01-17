package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.service.UserBarcodeService;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kiosk")
public class UserBarcodeController {
    @Autowired
    UserBarcodeService userBarcodeService;

    @GetMapping("/userinfo")
    public Object getUserInfoByCodeNumber(@RequestParam String codeNumber) {
        try {
            return userBarcodeService.getUserInfoByCodeNumber(codeNumber);
        } catch(Exception e) {
            e.printStackTrace();
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }
    }
}
