
package com.devcoop.kiosk.domain.service;
import com.devcoop.kiosk.domain.presentation.dto.PaymentRequestDto;
import org.springframework.http.ResponseEntity;

public interface PaymentsService {

    ResponseEntity<Object> processPaymentAndSaveReceipts(PaymentRequestDto requestDto, String token);

    // 다른 서비스 메서드들을 추가할 수 있음
}