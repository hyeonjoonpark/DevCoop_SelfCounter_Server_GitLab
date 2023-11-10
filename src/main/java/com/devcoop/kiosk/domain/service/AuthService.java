package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.presentation.dto.ResponseDto;
import com.devcoop.kiosk.domain.provider.TokenProvider;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public ResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        String codeNumber = dto.getCodeNumber();
        String pin = dto.getPin();

        try {
            boolean exists = userRepository.existsByCodeNumberAndPin(codeNumber, pin);
            System.out.println(exists);
            if (!exists) {
                return ResponseDto.setFailed("Login Info is Wrong");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseDto.setFailed("Database Error");
        }

        UserEntity userEntity;

        try {
            userEntity = userRepository.findByCodeNumber(codeNumber);
            System.out.println(userEntity);
            if (userEntity == null) {
                return ResponseDto.setFailed("User not found");
            }
        } catch(Exception e) {
            return ResponseDto.setFailed("Database Error");
        }

        userEntity.setCodeNumber("");

        String token = tokenProvider.createJwt(codeNumber);
        System.out.println(token);
        int exprTime = 600000; // 10 minutes

        LoginResponseDto loginResponseDto = new LoginResponseDto(token, exprTime, userEntity);
        System.out.println(loginResponseDto);
        return ResponseDto.setSuccess("Login Success", loginResponseDto);
    }

    public String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String decodeBase64(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes);
    }
}
