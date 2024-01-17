package com.devcoop.kiosk.domain.item.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemResponseDto {
    private String name;
    private int price;
    private String itemId;
}