package com.devcoop.kiosk.domain.item.presentation.dto;

import com.devcoop.kiosk.domain.item.types.EventType;
import lombok.Builder;

@Builder
public record EventItemResponse(
        String barcode,
        String itemName,
        int itemPrice,
        EventType event,
        String image // image 경로
) {

}
