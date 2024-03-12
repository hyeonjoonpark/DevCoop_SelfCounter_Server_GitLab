package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.paylog.PayLog;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayLogRequestDto {
  private String codeNumber;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime date;
  private Short type;
  private int innerPoint;
  private String chargerId;
  private String verifyKey;
  private String studentName;

  public PayLog toEntity() {
    return PayLog.builder()
      .codeNumber(codeNumber)
      .date(LocalDateTime.now())
      .innerPoint(innerPoint)
      .chargerId("kiosk")
      .verifyKey("test")
      .studentName(studentName)
      .build();
  }
}
