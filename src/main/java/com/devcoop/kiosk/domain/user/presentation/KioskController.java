package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.user.presentation.dto.PaymentsDto;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.paylog.service.KioskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class KioskController {
    private final KioskService kioskService;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public KioskController(KioskService kioskService, TransactionTemplate transactionTemplate) {
        this.kioskService = kioskService;
        this.transactionTemplate = transactionTemplate;
    }

    @PostMapping("/executePayments")
    public ResponseEntity<Object> executeTransactions(@RequestBody PaymentsDto paymentsDto) {
        return transactionTemplate.execute(new TransactionCallback<ResponseEntity<Object>>() {
            @Override
            public ResponseEntity<Object> doInTransaction(TransactionStatus transactionStatus) {
                try {
                    UserPointRequestDto userPointRequestDto = paymentsDto.getUserPointRequestDto();
                    Object result = kioskService.deductPoints(userPointRequestDto);
                    if (result instanceof ResponseEntity && ((ResponseEntity<?>) result).getStatusCode().isError()) {
                        throw new RuntimeException("결제를 하는 동안 에러가 발생하였습니다");
                    }

                    PayLogRequestDto payLogRequestDto = paymentsDto.getPayLogRequestDto();
                    ResponseEntity<Object> responseEntity = kioskService.savePayLog(payLogRequestDto);
                    if (responseEntity.getStatusCode().isError()) {
                        throw new RuntimeException("결제 정보를 저장하는 동안 에러가 발생하였습니다");
                    }

                    ResponseEntity<Object> saveReceiptResponseEntity = kioskService.saveReceipt(paymentsDto.getKioskDto());
                    if (saveReceiptResponseEntity.getStatusCode().isError()) {
                        throw new RuntimeException("영수증을 출력하는 동안 에러가 발생하였습니다");
                    }

                    // 모든 트랜잭션 성공 시 200 OK 응답 반환
                    return ResponseEntity.ok().build();
                } catch (Exception e) {
                    // 트랜잭션 롤백
                    transactionStatus.setRollbackOnly();
                    return ResponseEntity.internalServerError().build();
                }
            }
        });
    }
}
