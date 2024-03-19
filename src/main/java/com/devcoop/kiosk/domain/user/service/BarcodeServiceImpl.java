package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserResponse;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BarcodeServiceImpl implements UserBarcodeService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getUserInfoByCodeNumber(String codeNumber) throws GlobalException {
        try {
            User user = userRepository.findByCodeNumber(codeNumber);

            if (user != null) {
                UserResponse userResponse = new UserResponse();
                userResponse.setStudentName(user.getStudentName());
                userResponse.setPoint(user.getPoint());

                return (ResponseEntity<Object>) ResponseEntity.ok();
            } else {
                ErrorCode userNotFound = ErrorCode.USER_NOT_FOUND;
                System.out.println(userNotFound);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // 예외가 발생한 경우 500 Internal Server Error로 응답
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
