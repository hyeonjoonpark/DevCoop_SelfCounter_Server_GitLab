package com.devcoop.kiosk.domain.service;
import com.devcoop.kiosk.domain.presentation.dto.PaymentRequestDto;
import com.devcoop.kiosk.domain.provider.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final ReceiptService receiptService;
    private final LogService logService;
    private final TokenProvider tokenProvider;

    @Autowired
    public PaymentsServiceImpl(
            ReceiptService receiptService,
            LogService logService,
            TokenProvider tokenProvider
    ) {
        this.receiptService = receiptService;
        this.logService = logService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public ResponseEntity<Object> processPaymentAndSaveReceipts(PaymentRequestDto requestDto) {
        // 여기에서 userBarcode와 items를 사용하여 결제 및 영수증 저장 로직을 구현합니다.
        // receiptService와 logService를 사용하여 필요한 처리를 수행합니다.

        // 예시: 토큰에서 codeNumber 추출
        System.out.println("Test Start");
        System.out.println(requestDto);
        String codeNumber = tokenProvider.extractCodeNumberFromToken(requestDto.getToken());
        System.out.println(codeNumber);
//        // 예시: 영수증 저장
//        receiptService.saveReceipt(requestDto, codeNumber);
//
//        // 예시: 결제 정보 로그 저장
//        logService.savePayLog(requestDto, codeNumber);

        // 처리 결과를 반환
        return ResponseEntity.ok().build();
    }
}