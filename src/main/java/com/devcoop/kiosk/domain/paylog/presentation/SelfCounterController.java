package com.devcoop.kiosk.domain.paylog.presentation;

import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PaymentsDto;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.paylog.service.SelfCounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class SelfCounterController {
  private final SelfCounterService selfCounterService;
  private final TransactionTemplate transactionTemplate;

  @PostMapping("/executePayments")
  public ResponseEntity<Object> executeTransactions(PaymentsDto paymentsDto) {
    log.info("paymentsDto = {}", paymentsDto);
    return transactionTemplate.execute(new TransactionCallback<ResponseEntity<Object>>() {
      @Override
      public ResponseEntity<Object> doInTransaction(TransactionStatus transactionStatus) {
        try {
          UserPointRequestDto userPointRequestDto = paymentsDto.getUserPointRequestDto();
          log.info("userPointRequestDto = {}", userPointRequestDto);
          Object result = selfCounterService.deductPoints(userPointRequestDto);
          log.info("result = {}", result);
          if (result instanceof ResponseEntity && ((ResponseEntity<?>) result).getStatusCode().isError()) {
            throw new RuntimeException("결제를 하는 동안 에러가 발생하였습니다");
          }

          PayLogRequestDto payLogRequestDto = paymentsDto.getPayLogRequestDto();
          log.info("payLogRequestDto = {}", payLogRequestDto);
          ResponseEntity<Object> responseEntity = selfCounterService.savePayLog(payLogRequestDto);
          log.info("responseEntity = {}", responseEntity);
          if (responseEntity.getStatusCode().isError()) {
            throw new RuntimeException("결제 정보를 저장하는 동안 에러가 발생하였습니다");
          }

          ResponseEntity<Object> saveReceiptResponseEntity = selfCounterService.saveReceipt(paymentsDto.getKioskDto());
          log.info("saveReceiptResponseEntity = {}", saveReceiptResponseEntity);
          if (saveReceiptResponseEntity.getStatusCode().isError()) {
            throw new RuntimeException("영수증을 출력하는 동안 에러가 발생하였습니다");
          }

          // 모든 트랜잭션 성공 시 200 OK 응답 반환
          return ResponseEntity.ok().build();
        } catch (Exception e) {
          // 트랜잭션 롤백
          transactionStatus.setRollbackOnly();
          return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }
    });
  }
}
