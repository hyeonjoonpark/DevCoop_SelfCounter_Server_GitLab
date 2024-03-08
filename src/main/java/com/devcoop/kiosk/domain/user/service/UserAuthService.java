package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.user.utils.JwtUtil;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
  public LoginResponseDto login(LoginRequestDto dto) throws GlobalException {
    String codeNumber = dto.getCodeNumber();
    String pin = dto.getPin();

    boolean isUserExisted = userRepository.existsByCodeNumber(codeNumber);

    if (!isUserExisted) {
      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
    }

//    String dbPin = userRepository.findPinByCodeNumberAndPin(codeNumber, pin);
//
//    if (dbPin == null) {
//      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
//    }

    /**
     * ISSUE: isPinMatched 에서 NPE 발생
     */
//    boolean isPinMatched = bCryptPasswordEncoder.matches(pin, dbPin);
//
//    if(!isPinMatched) {
//      throw new GlobalException(ErrorCode.BARCODE_NOT_VALID);
//    }

    String token = JwtUtil.createJwt(codeNumber, secretKey, exprTime);

    User user = userRepository.findByCodeNumber(codeNumber);
    System.out.println("user = " + user);
    System.out.println("user.getStudentName() = " + user.getStudentName());

    LoginResponseDto loginResponseDto = LoginResponseDto.builder()
      .token(token)
      .studentNumber(user.getStudentNumber())
      .codeNumber(user.getCodeNumber())
      .studentName(user.getStudentName())
      .point(user.getPoint())
      .build();

    return loginResponseDto;

  }
}
