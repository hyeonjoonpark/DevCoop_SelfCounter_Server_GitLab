package com.devcoop.kiosk.domain.user.presentation.dto;

import com.devcoop.kiosk.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    @NotBlank
    private String token;
    @NotBlank
    private int exprTime;
    private User user;
}