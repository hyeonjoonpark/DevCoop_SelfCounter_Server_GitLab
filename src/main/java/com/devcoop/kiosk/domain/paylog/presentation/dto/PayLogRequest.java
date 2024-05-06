package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.paylog.PayLog;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PayLogRequest(
  @NotBlank(message = "바코드는 필수 입력사항 입니다")
  String codeNumber,
  int innerPoint,
  String studentName
) {

  public PayLog toEntity() {
    return PayLog.builder()
      .codeNumber(codeNumber)
      .innerPoint(innerPoint)
      .chargerId("kiosk")
      .verifyKey("test")
      .studentName(studentName)
      .build();
  }
}
