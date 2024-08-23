package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequest;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponse;
import com.devcoop.kiosk.domain.user.presentation.dto.PinChangeRequest;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import com.devcoop.kiosk.global.utils.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@RequiredArgsConstructor
public class UserAuthService {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Long exprTime = 1000 * 60 * 60L;

    @Value("${jwt.secret}")
    private String secretKey;

    @Transactional
    public LoginResponse login(LoginRequest dto) throws GlobalException {
        String userCode = dto.getUserCode();
        String userPin = dto.getUserPin();
        logger.info(userPin);
        logger.info("Attempting login foa userCode: {}", userCode);

        User user = userRepository.findByUserCode(userCode);

        if (user == null) {
            logger.error("User not found for userCode: {}", userCode);
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }

        logger.info("User found: {} - Verifying PIN", user.getUserName());

        if (!bCryptPasswordEncoder.matches(userPin, user.getUserPin())) {
            logger.error("Invalid PIN for userCode: {}", userCode);
            throw new RuntimeException("핀 번호가 옳지 않습니다");
        }

        logger.info("PIN verified for userCode: {} - Generating JWT token", userCode);

        String token = JwtUtil.createJwt(userCode, secretKey, exprTime);

        logger.info("JWT token generated for userCode: {}", userCode);

        return LoginResponse.builder()
                .token(token)
                .userNumber(user.getUserNumber())
                .userCode(user.getUserCode())
                .userName(user.getUserName())
                .userPoint(user.getUserPoint())
                .build();
    }

    @Transactional
    public void changePassword(PinChangeRequest dto) throws GlobalException {
        String userCode = dto.getUserCode(); // codeNumber를 userCode로 변경
        String userPin = dto.getUserPin(); // pin을 userPin으로 변경
        String newPin = dto.getNewPin();

        User user = userRepository.findByUserCode(userCode); // 메서드 호출 수정

        if (user == null) {
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }

        if (!bCryptPasswordEncoder.matches(userPin, user.getUserPin())) {
            throw new RuntimeException("현재 핀 번호가 옳지 않습니다");
        }

        if (bCryptPasswordEncoder.matches(newPin, user.getUserPin())) {
            throw new RuntimeException("현재 핀번호와 다른 핀번호를 입력해주세요");
        }

        String encodedPin = bCryptPasswordEncoder.encode(newPin);

        user.changePin(encodedPin); // update 메서드를 changePin으로 변경
        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(String userCode) throws GlobalException {
        String resetPassword = "1234";

        User user = userRepository.findByUserCode(userCode); // 메서드 호출 수정

        if (user == null) {
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }

        user.changePin(bCryptPasswordEncoder.encode(resetPassword)); // update 메서드를 changePin으로 변경
        userRepository.save(user);
    }
}
