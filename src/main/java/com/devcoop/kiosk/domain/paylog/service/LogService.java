package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
  ResponseEntity<Object> savePayLog(PayLogRequest payLogRequest);
}
