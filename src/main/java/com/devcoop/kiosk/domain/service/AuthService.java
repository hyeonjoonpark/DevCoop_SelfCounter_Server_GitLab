package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.presentation.dto.ResponseDto;
import com.devcoop.kiosk.domain.provider.TokenProvider;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class AuthService {
    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthService(UserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    public ResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        String codeNumber = dto.getCodeNumber();
        String pin = dto.getPin();

        try {
            // UserDetailsService를 사용하여 사용자 정보를 가져옴
            UserDetails userDetails = userDetailsService.loadUserByUsername(codeNumber);

            // 비밀번호 검증 로직 필요 (BCrypt 등 사용)
            // 비밀번호가 일치하지 않으면 예외 처리

            // 인증 성공 시 토큰 생성
            String token = tokenProvider.createJwt(codeNumber);

            // 로그인 응답을 반환하고 토큰을 포함
            int exprTime = 600000; // 10 minutes
            LoginResponseDto loginResponseDto = new LoginResponseDto(token, exprTime, userDetails);
            return ResponseDto.setSuccess("Login Success", loginResponseDto, token);
        } catch (Exception e) {
            return ResponseDto.setFailed("Login Error");
        }
    }


    public String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String decodeBase64(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes);
    }
}
