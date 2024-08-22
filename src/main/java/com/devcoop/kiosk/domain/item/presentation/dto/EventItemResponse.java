package com.devcoop.kiosk.domain.item.presentation.dto;

import com.devcoop.kiosk.domain.item.types.EventType;
import lombok.Builder;

@Builder
public record EventItemResponse(
        String itemCode,  // barcode를 itemCode로 수정
        String itemName,
        int itemPrice,
        EventType event,
        String image // 이미지 경로
) {

}
