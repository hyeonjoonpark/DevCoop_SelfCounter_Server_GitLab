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

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long exprTime = 1000 * 60 * 60L;

    public LoginResponseDto login(LoginRequestDto dto) throws GlobalException {
        String codeNumber = dto.getCodeNumber();
        String pin = dto.getPin();

        try {
          boolean isUserExisted = userRepository.existsByCodeNumberAndPin(codeNumber, pin);

          if(!isUserExisted) {
              throw new GlobalException(ErrorCode.USER_NOT_FOUND);
          }

          String dbPin = userRepository.findPinByCodeNumberAndPin(codeNumber, pin);

          boolean isPinMatched = bCryptPasswordEncoder.matches(pin, dbPin);
          System.out.println("isPinMatched = " + isPinMatched);

          String token = jwtUtil.createJwt(codeNumber, secretKey, exprTime);

          User user = userRepository.findUserDetailByCodeNumberAndPin(codeNumber, pin);

          return new LoginResponseDto(token, user.getStudentNumber(), user.getCodeNumber(), user.getStudentName(), user.getPoint());

        } catch (GlobalException e) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
