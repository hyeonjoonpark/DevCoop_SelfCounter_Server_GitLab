package com.devcoop.kiosk.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private String userNumber; // studentNumber -> userNumber
    private String userCode;   // codeNumber -> userCode
    private String userName;   // studentName -> userName
    private int userPoint;     // point -> userPoint
}
