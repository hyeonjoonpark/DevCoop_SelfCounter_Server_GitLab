package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.presentation.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserBarcodeService {
    ResponseEntity<UserResponseDto> getUserInfoByCodeNumber(String codeNumber);
}
