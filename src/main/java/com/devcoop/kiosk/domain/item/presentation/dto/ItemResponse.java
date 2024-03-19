package com.devcoop.kiosk.domain.item.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemResponse {
    private String name;
    private int price;
}
