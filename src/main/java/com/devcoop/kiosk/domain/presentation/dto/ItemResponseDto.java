package com.devcoop.kiosk.domain.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
    private String name;
    private int price;
    private String ItemBarcode;
}