package com.devcoop.kiosk.domain.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private String itemName; // 아이템 이름
    private int saleQty; // 판매 수량
    private int dcmSaleAmt; // 판매 가격

    // 생성자, 게터, 세터 등도 자동으로 생성됨
}