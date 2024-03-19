package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.paylog.PayLog;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PayLogRequest(
  @NotBlank(message = "바코드는 필수 입력사항 입니다")
  String codeNumber,
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  LocalDate date,
  Short type,
  int innerPoint,
  int chargerId,
  String verifyKey,
  String studentName
) {

  public PayLogRequest(String codeNumber, LocalDate date, Short type, int innerPoint, int chargerId, String verifyKey, String studentName) {
    this.codeNumber = codeNumber;
    this.date = date;
    this.type = type;
    this.innerPoint = innerPoint;
    this.chargerId = chargerId;
    this.verifyKey = verifyKey;
    this.studentName = studentName;
  }

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
