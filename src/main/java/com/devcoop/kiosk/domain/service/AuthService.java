package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.details.CustomUserDetails;
import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.provider.TokenProvider;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.util.Base64;

@Service
public class AuthService {
    private final CustomUserDetailsService customUserDetailsService;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(CustomUserDetailsService customUserDetailsService, TokenProvider tokenProvider,
                       UserRepository userRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> login(LoginRequestDto dto) {
        String codeNumber = dto.getCodeNumber();
        String pin = dto.getPin();
        System.out.println("Login Start");
        try {
            // UserDetailsService를 사용하여 사용자 정보를 가져옴
            CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(codeNumber);

            if (customUserDetails == null) {
                // 사용자가 존재하지 않는 경우
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found or Wrong Password");
            }

            int point = customUserDetails.getPoint();
            String studentName = customUserDetails.getUsername();

            UserEntity userEntity;
            userEntity = userRepository.findByCodeNumber(codeNumber);

            // 저장된 BCrypt 해시와 입력된 비밀번호를 비교
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean passwordMatches = passwordEncoder.matches(pin, customUserDetails.getPassword());

            if (!passwordMatches) {
                // 패스워드가 일치하지 않는 경우
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found or Wrong Password");
            }

            // 인증 성공 시 토큰 생성
            String token = tokenProvider.createJwt(codeNumber);

            // 로그인 응답을 반환하고 토큰을 포함
            int exprTime = 600000; // 10 minutes
            LoginResponseDto loginResponseDto = new LoginResponseDto(token, exprTime, userEntity);
            System.out.println(loginResponseDto);
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        } catch (Exception e) {
            System.out.println("Error!");
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login Error");
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