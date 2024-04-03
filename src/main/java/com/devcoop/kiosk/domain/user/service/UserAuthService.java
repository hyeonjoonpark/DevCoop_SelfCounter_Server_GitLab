package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequest;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponse;
import com.devcoop.kiosk.domain.user.utils.JwtUtil;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Value("${jwt.secret}")
  private String secretKey;

  private Long exprTime = 1000 * 60 * 60L;

  @Transactional
  public LoginResponse login(LoginRequest dto) throws GlobalException {
    String codeNumber = dto.getCodeNumber();
    String pin = dto.getPin();

    User user = userRepository.findByCodeNumber(codeNumber);

    if (user == null) {
      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
    }

    if(!bCryptPasswordEncoder.matches(pin, user.getPin())) {
      throw new RuntimeException("핀 번호가 옳지 않습니다");
    }

    String token = JwtUtil.createJwt(codeNumber, secretKey, exprTime);

    System.out.println("user = " + user);
    System.out.println("user.getStudentName = " + user.getStudentName());

    LoginResponse loginResponse = LoginResponse.builder()
      .token(token)
      .studentNumber(user.getStudentNumber())
      .codeNumber(user.getCodeNumber())
      .studentName(user.getStudentName())
      .point(user.getPoint())
      .build();

    return loginResponse;

  }
}
