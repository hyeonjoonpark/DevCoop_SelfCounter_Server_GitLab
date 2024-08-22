package com.devcoop.kiosk.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record UserPointRequest(
    @NotBlank(message = "바코드 번호는 필수값입니다") String userCode, // codeNumber -> userCode
    int totalPrice
) {

}
