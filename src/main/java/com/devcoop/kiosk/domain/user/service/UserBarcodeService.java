package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.presentation.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserBarcodeService {
    ResponseEntity<UserResponseDto> getUserInfoByCodeNumber(String codeNumber);
}
