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

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Long exprTime = 1000 * 60 * 60L;

    @Value("${jwt.secret}")
    private String secretKey;

    @Transactional
    public LoginResponse login(LoginRequest dto) throws GlobalException {
        String userCode = dto.getUserCode(); // codeNumber를 userCode로 변경
        String userPin = dto.getUserPin(); // pin을 userPin으로 변경

        User user = userRepository.findByUserCode(userCode); // 메서드 호출 수정

        if (user == null) {
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }

        if (!bCryptPasswordEncoder.matches(userPin, user.getUserPin())) {
            throw new RuntimeException("핀 번호가 옳지 않습니다");
        }

        String token = JwtUtil.createJwt(userCode, secretKey, exprTime);

        return LoginResponse.builder()
                .token(token)
                .userNumber(user.getUserNumber()) // studentNumber를 userNumber로 변경
                .userCode(user.getUserCode()) // codeNumber를 userCode로 변경
                .userName(user.getUserName()) // studentName을 userName으로 변경
                .userPoint(user.getUserPoint()) // point를 userPoint로 변경
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
