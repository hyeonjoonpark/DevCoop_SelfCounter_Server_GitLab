package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.UserResponseDto;
import com.devcoop.kiosk.domain.repository.UserRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserBarcodeServiceImpl implements UserBarcodeService {

    private final UserRepository userRepository;

    @Autowired
    public UserBarcodeServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserResponseDto> getUserInfoByCodeNumber(String codeNumber) {
        try {
            UserEntity userEntity = userRepository.findByCodeNumber(codeNumber);

            if (userEntity != null) {
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setStudentName(userEntity.getStudentName());
                userResponseDto.setPoint(userEntity.getPoint());

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
