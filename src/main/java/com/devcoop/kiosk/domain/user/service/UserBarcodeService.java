package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.global.exception.GlobalException;
import org.springframework.http.ResponseEntity;

public interface UserBarcodeService {
    ResponseEntity<Object> getUserInfoByCodeNumber(String codeNumber) throws GlobalException;
}
