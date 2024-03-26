package com.devcoop.kiosk.domain.paylog.presentation;

import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.paylog.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "payLog", description = "Paylog API")
public class SaveLogController {
  private final LogService logService;
  @PostMapping("/save/paylog")
  @Operation(summary = "paylog", description = "결제 기록 저장")
  public void savePayLog(@RequestBody PayLogRequest payLogRequest) {
    logService.savePayLog(payLogRequest);
  }
}
