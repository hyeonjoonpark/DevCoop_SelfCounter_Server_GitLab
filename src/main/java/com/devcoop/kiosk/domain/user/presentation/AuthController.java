package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.user.service.UserAuthService;
import com.devcoop.kiosk.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserAuthService userAuthService;
    @PostMapping("/signIn")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws GlobalException {
        LoginResponseDto result = userAuthService.login(loginRequestDto);
        return result;
    }
}
