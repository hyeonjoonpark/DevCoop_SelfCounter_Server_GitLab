package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.service.UserPointService;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class UserPointController {
    @Autowired UserPointService userPointService;
    @PutMapping("/pay")
    public Object deductPoints(@RequestBody UserPointRequestDto requestDto) {
        try {
            return userPointService.deductPoints(requestDto);
        } catch(Exception e) {
            e.printStackTrace();
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }
    }
}
