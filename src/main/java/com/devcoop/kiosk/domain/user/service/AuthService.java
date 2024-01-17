package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.details.CustomUserDetails;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginRequestDto;
import com.devcoop.kiosk.domain.user.presentation.dto.LoginResponseDto;
import com.devcoop.kiosk.domain.user.utils.TokenProvider;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long exprTime = 1000 * 60 * 60L;

    public String login(LoginRequestDto dto) throws GlobalException {
        String codeNumber = dto.getCodeNumber();
        String pin = dto.getPin();

        try {
            boolean isUserExisted = userRepository.existsByCodeNumberAndPin(codeNumber, pin);

            if(!isUserExisted) {
                throw new GlobalException(ErrorCode.USER_NOT_FOUND);
            }

            String token = tokenProvider.createJwt(codeNumber, secretKey, exprTime);

            return token;
        } catch (GlobalException e) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}