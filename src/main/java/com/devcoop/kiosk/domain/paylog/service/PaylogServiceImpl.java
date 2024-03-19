package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaylogServiceImpl implements LogService {

  private final PayLogRepository payLogRepository;

  @Override
  public ResponseEntity<Object> savePayLog(PayLogRequest payLogRequest) {
    try {
      PayLog payLog = payLogRequest.toEntity();

      payLogRepository.save(payLog);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
