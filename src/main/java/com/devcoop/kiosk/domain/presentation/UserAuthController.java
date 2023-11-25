package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class UserAuthController {
    @Autowired AuthService authService;
    @PostMapping("/auth/signIn")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        ResponseEntity<Object> result = authService.login(loginRequestDto);
        return result;
    }
}
