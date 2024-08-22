package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaylogServiceImpl implements LogService {

  private final PayLogRepository payLogRepository;
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<Object> savePayLog(PayLogRequest payLogRequest) {
    try {
      User user = userRepository.findByUserEmail(payLogRequest.managedEmail());

      PayLog payLog = payLogRequest.toEntity(user.getUserPoint());

      payLogRepository.save(payLog);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
