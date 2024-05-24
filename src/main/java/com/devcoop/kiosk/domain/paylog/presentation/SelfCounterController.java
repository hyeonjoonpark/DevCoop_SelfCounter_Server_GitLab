package com.devcoop.kiosk.domain.paylog.presentation;

import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.paylog.presentation.dto.Payments;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
import com.devcoop.kiosk.domain.paylog.service.SelfCounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Kiosk", description = "Kiosk API")
public class SelfCounterController {
  private final SelfCounterService selfCounterService;

  @PostMapping(value = "/executePayments")
  @Operation(summary = "kiosk service", description = "키오스크 전반적인 API")
  @Transactional
  public ResponseEntity<Map<String, Object>> executeTransactions(@RequestBody Payments payments) {
      log.info("paymentsDto = {}", payments);
      try {
          // 포인트 차감
          UserPointRequest userPointRequestDto = payments.userPointRequest();
          log.info("userPointRequestDto = {}", userPointRequestDto);
          int remainingPoints = selfCounterService.deductPoints(userPointRequestDto);
          
          // 결제 로그 저장
          PayLogRequest payLogRequestDto = payments.payLogRequest();
          log.info("payLogRequestDto = {}", payLogRequestDto);
          selfCounterService.savePayLog(payLogRequestDto);

          // 영수증 저장
          selfCounterService.saveReceipt(payments.kioskRequest());

          // 모든 트랜잭션 성공
          log.info("모든 트랜잭션 성공");
          Map<String, Object> response = new HashMap<>();
          response.put("status", "success");
          response.put("message", "결제가 성공적으로 완료되었습니다.");
          response.put("remainingPoints", remainingPoints);
          return ResponseEntity.ok(response);
      } catch (Exception e) {
          log.error("트랜잭션 실패 및 롤백", e);
          Map<String, Object> errorResponse = new HashMap<>();
          errorResponse.put("status", "error");
          errorResponse.put("message", "오류가 발생하였습니다: " + e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
      }
  }
}
