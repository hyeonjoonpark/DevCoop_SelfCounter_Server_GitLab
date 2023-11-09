package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.presentation.dto.ResponseDto;
import com.devcoop.kiosk.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class UserAuthController {
    @Autowired AuthService authService;
    @PostMapping("/auth/signIn")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        ResponseDto<LoginResponseDto> result = authService.login(loginRequestDto);
        return result;
    }
}
