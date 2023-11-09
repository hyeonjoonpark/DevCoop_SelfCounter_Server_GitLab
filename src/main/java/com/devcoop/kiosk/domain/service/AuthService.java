package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.presentation.dto.ResponseDto;
import com.devcoop.kiosk.domain.provider.TokenProvider;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenProvider tokenProvider;

    public ResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        String codeNumber = dto.getCodeNumber();
        String pin = dto.getPin();

        try {
            boolean existed =  userRepository.existsByCodeNumberAndPin(codeNumber, pin);
            if(!existed) {
                return ResponseDto.setFailed("Login Info is Wrong");
            }
        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error");
        }

        UserEntity userEntity = null;
        try {
            // 값이 존재하면
            userEntity = userRepository.findById(Integer.valueOf(codeNumber)).get(); // 사용자 바코드를 가져옴
        } catch(Exception e) {
            return ResponseDto.setFailed("Database Error");
        }

        userEntity.setCodeNumber("");

        String token = tokenProvider.createJwt(codeNumber);
        int exprTime = 600000; // 10분

        LoginResponseDto loginResponseDto = new LoginResponseDto(token, exprTime, userEntity);
        return ResponseDto.setSuccess("Login Success", loginResponseDto);
    }
}