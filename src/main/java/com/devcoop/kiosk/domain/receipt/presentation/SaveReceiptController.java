package com.devcoop.kiosk.domain.receipt.presentation;

import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.receipt.service.ReceiptService;
import com.devcoop.kiosk.global.exception.GlobalException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Kiosk_Receipt_Save", description = "Receipt Save API")
public class SaveReceiptController {
  private final ReceiptService receiptService;

  @PostMapping("/save/receipt")
  @Operation(summary = "save receipt", description = "키오스크에서 발행된 영수증 저장")
  public ResponseEntity<String> saveReceipt(@RequestBody KioskRequest kioskRequest) throws GlobalException {
    receiptService.save(kioskRequest);
    return ResponseEntity.ok("영수증 발행 성공");
  }
}
