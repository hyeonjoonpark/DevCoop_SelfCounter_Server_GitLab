package com.devcoop.kiosk.domain.item.presentation.dto;

import lombok.Builder;

@Builder
public record ItemResponse(
        String itemName,   // name을 itemName으로 변경
        int itemPrice,
        int quantity,
        String eventStatus // EventType 대신 String 타입 사용
) {

}
