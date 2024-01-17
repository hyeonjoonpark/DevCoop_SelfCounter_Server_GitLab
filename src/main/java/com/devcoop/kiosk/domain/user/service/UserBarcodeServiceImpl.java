package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserResponseDto;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBarcodeServiceImpl implements UserBarcodeService {

    private final UserRepository userRepository;

    public ResponseEntity<UserResponseDto> getUserInfoByCodeNumber(String codeNumber) {
        try {
            User user = userRepository.findByCodeNumber(codeNumber);

            if (user != null) {
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setStudentName(user.getStudentName());
                userResponseDto.setPoint(user.getPoint());

                return ResponseEntity.ok(userResponseDto);
            } else {
                ErrorCode userNotFound = ErrorCode.USER_NOT_FOUND;
                System.out.println(userNotFound);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // 예외가 발생한 경우 500 Internal Server Error로 응답
            return ResponseEntity.status(500).build();
        }
    }
}
