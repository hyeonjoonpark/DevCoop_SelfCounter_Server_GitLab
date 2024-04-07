package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequest;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponse;
import com.devcoop.kiosk.domain.user.service.UserAuthService;
import com.devcoop.kiosk.global.exception.GlobalException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name= "SignIn", description = "SignIn API")
public class AuthController {
  private final UserAuthService userAuthService;

  @PostMapping("/signIn")
  @Operation(summary = "sign in", description = "로그인")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) throws GlobalException {
    LoginResponse result = userAuthService.login(loginRequest);
    return result;
  }

  @PutMapping("/pwChange")
  @Operation(summary = "change password", description = "비밀번호 변경")
  public void changePassword(@RequestBody LoginRequest loginRequest) throws GlobalException {
    userAuthService.changePassword(loginRequest);
  }
}
