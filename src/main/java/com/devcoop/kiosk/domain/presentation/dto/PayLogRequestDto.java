package com.devcoop.kiosk.domain.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayLogRequestDto {
    private String codeNumber;
    private ZonedDateTime date;
    private Short type;
    private int innerPoint;
    private String chargerId;
    private String verifyKey;
    private String studentName;
}