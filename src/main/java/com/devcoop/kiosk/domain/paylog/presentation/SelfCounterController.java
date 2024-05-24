package com.devcoop.kiosk.domain.paylog.presentation;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcoop.kiosk.domain.paylog.service.SelfCounterService;
import com.devcoop.kiosk.domain.paylog.dto.Payments;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Kiosk", description = "Kiosk API")
public class SelfCounterController {
  private final SelfCounterService selfCounterService;

  @PostMapping(value = "/executePayments")
  @Operation(summary = "kiosk service", description = "키오스크 전반적인 API")
  public ResponseEntity<Map<String, Object>> executeTransactions(@RequestBody Payments payments) {
      log.info("paymentsDto = {}", payments);
      try {
          Map<String, Object> response = selfCounterService.executeAllTransactions(payments);
          return ResponseEntity.ok(response);
      } catch (Exception e) {
          log.error("트랜잭션 실패 및 롤백", e);
          Map<String, Object> errorResponse = new HashMap<>();
          errorResponse.put("status", "error");
          errorResponse.put("message", "오류가 발생하였습니다\n" + e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
      }
  }
}
