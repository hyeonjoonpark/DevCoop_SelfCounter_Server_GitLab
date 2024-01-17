package com.devcoop.kiosk.domain.user.presentation.dto;

import com.devcoop.kiosk.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    @NotBlank
    private String token;
    @NotBlank
    private int exprTime;
    private User user;
}