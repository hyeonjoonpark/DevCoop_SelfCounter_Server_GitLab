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
      // 결제 전 사용자 포인트 (beforePoint)
      User user = userRepository.findByUserEmail(payLogRequest.managedEmail());
      int beforePoint = user.getUserPoint();

      // 결제된 포인트만큼 차감하여 afterPoint 계산
      int afterPoint = beforePoint - payLogRequest.payedPoint();

      // toEntity 메서드에 beforePoint와 afterPoint를 모두 전달
      PayLog payLog = payLogRequest.toEntity(beforePoint, afterPoint);

      // PayLog 저장
      payLogRepository.save(payLog);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      // 오류 발생 시 500 응답 반환
      return ResponseEntity.internalServerError().build();
    }
  }
}
