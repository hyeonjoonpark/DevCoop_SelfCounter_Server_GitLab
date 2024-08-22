package com.devcoop.kiosk.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PinChangeRequest {
    private String userCode; // codeNumber -> userCode
    private String userPin;  // pin -> userPin
    private String newPin;
}
