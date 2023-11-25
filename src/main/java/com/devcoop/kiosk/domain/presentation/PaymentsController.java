package com.devcoop.kiosk.domain.presentation;
import com.devcoop.kiosk.domain.presentation.dto.PaymentRequestDto;
import com.devcoop.kiosk.domain.provider.TokenProvider;
import com.devcoop.kiosk.domain.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kiosk")
public class PaymentsController {

    private final PaymentsService paymentsService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @PostMapping("/payments")
    public ResponseEntity<Object> processPaymentAndSaveReceipts(@RequestBody PaymentRequestDto requestDto, @RequestHeader("Authorization") String authorizationHeader) {
        String codeNumber = tokenProvider.extractCodeNumberFromToken(authorizationHeader);
        // PaymentRequestDto에는 userBarcode와 items가 포함되어야 합니다.
        return paymentsService.processPaymentAndSaveReceipts(requestDto, codeNumber);

    }
}
