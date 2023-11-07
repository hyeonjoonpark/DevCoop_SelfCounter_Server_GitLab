package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.UserResponseDto;
import com.devcoop.kiosk.domain.repository.UserRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kiosk")
public class UserBarcodeController {
    private final UserRepository userRepository;

    public UserBarcodeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserResponseDto> getUserInfoByCodeNumber(
        @RequestParam String codeNumber
    ) {
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
            e.printStackTrace(); // 예외 정보를 로깅 또는 디버깅에 사용
            return ResponseEntity.status(500).build();
        }
    }
}
