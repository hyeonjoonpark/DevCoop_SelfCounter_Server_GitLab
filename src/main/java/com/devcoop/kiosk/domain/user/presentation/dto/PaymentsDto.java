package com.devcoop.kiosk.domain.user.presentation.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentsDto {
    private UserPointRequestDto userPointRequestDto;
    private PayLogRequestDto payLogRequestDto;
    private KioskDto kioskDto;
}
