package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.user.presentation.dto.PayLogRequestDto;
import org.springframework.http.ResponseEntity;

public interface LogService {
    ResponseEntity<Object> savePayLog(PayLogRequestDto payLogRequestDto);
}
