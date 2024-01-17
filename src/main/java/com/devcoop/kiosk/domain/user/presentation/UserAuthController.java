package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.user.service.AuthService;
import com.devcoop.kiosk.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
@RequiredArgsConstructor
public class UserAuthController {
    private final AuthService authService;
    @PostMapping("/auth/signIn")
    public String login(@RequestBody LoginRequestDto loginRequestDto) throws GlobalException {
        String result = authService.login(loginRequestDto);
        return result;
    }
}