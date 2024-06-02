package com.devcoop.kiosk.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record UserPointRequest(
  @NotBlank(message = "바코드 번호는 필수값입니다") String codeNumber,
  int totalPrice
) {

}
