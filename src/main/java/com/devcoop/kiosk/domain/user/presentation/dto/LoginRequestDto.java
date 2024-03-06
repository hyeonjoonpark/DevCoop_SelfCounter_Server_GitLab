package com.devcoop.kiosk.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "바코드 번호는 필수값입니다")
    private String codeNumber;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String pin;
}
