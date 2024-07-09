package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequest;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponse;
import com.devcoop.kiosk.domain.user.presentation.dto.PinChangeRequest;
import com.devcoop.kiosk.domain.user.service.UserAuthService;
import com.devcoop.kiosk.global.exception.GlobalException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "SignIn", description = "SignIn API")
public class AuthController {
    private final UserAuthService userAuthService;

    @PostMapping("/signIn")
    @Operation(summary = "login", description = "로그인")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws GlobalException {
        LoginResponse result = userAuthService.login(loginRequest);
        return result;
    }

    @PutMapping("/pwChange")
    @Operation(summary = "update password", description = "비밀번호 변경")
    public void changePassword(@RequestBody PinChangeRequest pinChangeRequest) throws GlobalException {
        userAuthService.changePassword(pinChangeRequest);
    }

    @PutMapping("/pw-reset")
    @Operation(summary = "reset password", description = "비밀번호 초기화")
    public ResponseEntity<?> resetPassword(@RequestBody String codeNumber) throws GlobalException {
        userAuthService.resetPassword(codeNumber);
        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 핀번호를 초기화하였습니다.");
    }
}
