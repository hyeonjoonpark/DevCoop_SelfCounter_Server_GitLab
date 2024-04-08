package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequest;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponse;
import com.devcoop.kiosk.domain.user.presentation.dto.PinChangeRequest;
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

    System.out.println("user = " + user.getPin());

    if(!bCryptPasswordEncoder.matches(pin, user.getPin())) {
      throw new RuntimeException("핀 번호가 옳지 않습니다");
    }

    String token = JwtUtil.createJwt(codeNumber, secretKey, exprTime);

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

  @Transactional
  public void changePassword(PinChangeRequest dto) throws GlobalException {
    String codeNumber = dto.getCodeNumber();
    String pin = dto.getPin();
    String newPin = dto.getNewPin();

    User user = userRepository.findByCodeNumber(codeNumber);
    System.out.println("user = " + user);

    if (user == null) {
      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
    }

    if(!bCryptPasswordEncoder.matches(pin, user.getPin())) {
      throw new RuntimeException("현재핀 번호가 옳지 않습니다");
    }

    if(bCryptPasswordEncoder.matches(newPin, user.getPin())) {
      throw new RuntimeException("현재 핀번호와 다른 핀번호를 입력해주세요");
    }

    String encodedPin = bCryptPasswordEncoder.encode(newPin);

    user.update(encodedPin);
    userRepository.save(user);
  }
}
