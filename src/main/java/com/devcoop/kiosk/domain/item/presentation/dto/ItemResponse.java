package com.devcoop.kiosk.domain.item.presentation.dto;

import com.devcoop.kiosk.domain.item.types.EventType;
import lombok.Builder;

@Builder
public record ItemResponse(
        String name,
        int price,
        int quantity,
        EventType eventStatus
) {

}
