package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.presentation.dto.PayLogRequestDto;
import org.springframework.http.ResponseEntity;

public interface LogService {
    ResponseEntity<Object> savePayLog(PayLogRequestDto payLogRequestDto);
}
