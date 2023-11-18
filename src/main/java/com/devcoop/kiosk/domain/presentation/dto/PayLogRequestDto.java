package com.devcoop.kiosk.domain.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayLogRequestDto {
    private String codeNumber;
    private LocalDate date;
    private Short type;
    private int innerPoint;
    private String chargerId;
    private String verifyKey;
    private String studentName;
}

