package com.devcoop.kiosk.domain.item.presentation.dto;

import com.devcoop.kiosk.domain.item.types.EventType;
import lombok.Builder;

@Builder
public record ItemResponse(
        String itemName,   // name을 itemName으로 변경
        int itemPrice,
        int quantity,
        EventType eventStatus
) {

}
