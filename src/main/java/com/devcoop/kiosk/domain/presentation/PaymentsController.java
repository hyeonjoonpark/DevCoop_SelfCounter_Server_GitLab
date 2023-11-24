package com.devcoop.kiosk.domain.presentation;
import com.devcoop.kiosk.domain.presentation.dto.PaymentRequestDto;
import com.devcoop.kiosk.domain.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kiosk")
public class PaymentsController {

    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @PostMapping("/payments")
    public ResponseEntity<Object> processPaymentAndSaveReceipts(@RequestBody PaymentRequestDto requestDto) {
        // PaymentRequestDto에는 userBarcode와 items가 포함되어야 합니다.
        return paymentsService.processPaymentAndSaveReceipts(requestDto);
    }
}
