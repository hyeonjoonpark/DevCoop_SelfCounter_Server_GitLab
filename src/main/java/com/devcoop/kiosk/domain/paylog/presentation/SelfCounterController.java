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
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Kiosk", description = "Kiosk API")
public class SelfCounterController {
  private final SelfCounterService selfCounterService;
  private final TransactionTemplate transactionTemplate;

  @PostMapping("/executePayments")
  @Operation(summary = "kiosk service", description = "키오스크 전반적인 API")
  public ResponseEntity<Object> executeTransactions(@RequestBody Payments payments) {
      log.info("paymentsDto = {}", payments);
      return transactionTemplate.execute(new TransactionCallback<ResponseEntity<Object>>() {
          @Override
          public ResponseEntity<Object> doInTransaction(TransactionStatus transactionStatus) {
              System.out.println("트랜잭션 시작");
              try {
                  // 포인트 차감
                  UserPointRequest userPointRequestDto = payments.userPointRequest();
                  log.info("userPointRequestDto = {}", userPointRequestDto);
                  ResponseEntity<Object> result = selfCounterService.deductPoints(userPointRequestDto);
                  log.info("deductPoints result = {}", result);
                  if (result.getStatusCode().isError()) {
                      String errorMsg = "포인트 차감 중 오류가 발생하였습니다";
                      log.error(errorMsg);
                      throw new RuntimeException(errorMsg);
                  }

                  // 결제 로그 저장
                  PayLogRequest payLogRequestDto = payments.payLogRequest();
                  log.info("payLogRequestDto = {}", payLogRequestDto);
                  ResponseEntity<Object> responseEntity = selfCounterService.savePayLog(payLogRequestDto);
                  log.info("savePayLog responseEntity = {}", responseEntity);
                  if (responseEntity.getStatusCode().isError()) {
                      String errorMsg = "결제 로그 저장 중 오류가 발생하였습니다";
                      log.error(errorMsg);
                      throw new RuntimeException(errorMsg);
                  }

                  // 영수증 저장
                  ResponseEntity<String> saveReceiptResponseEntity = selfCounterService.saveReceipt(payments.kioskRequest());
                  log.info("saveReceiptResponseEntity = {}", saveReceiptResponseEntity);
                  if (saveReceiptResponseEntity.getStatusCode().isError()) {
                      String errorMsg = "영수증 저장 중 오류가 발생하였습니다";
                      log.error(errorMsg);
                      throw new RuntimeException(errorMsg);
                  }

                  // 모든 트랜잭션 성공
                  log.info("모든 트랜잭션 성공");
                  return ResponseEntity.ok("결제가 성공적으로 완료되었습니다.");
              } catch (Exception e) {
                  log.error("트랜잭션 실패 및 롤백", e);
                  // 트랜잭션 롤백
                  transactionStatus.setRollbackOnly();
                  return new ResponseEntity<>("오류가 발생하였습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
              }
          }
      });
  }
}
