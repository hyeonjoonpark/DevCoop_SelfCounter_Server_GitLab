package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.user.service.UserPointService;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
@RequiredArgsConstructor
public class UserPointController {

    private final UserPointService userPointService;
    @PutMapping("/pay")
    public Object deductPoints(@RequestBody UserPointRequestDto requestDto) {
        try {
            System.out.println("요청옴");
            return userPointService.deductPoints(requestDto);
        } catch(Exception e) {
            e.printStackTrace();
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }
    }
}
