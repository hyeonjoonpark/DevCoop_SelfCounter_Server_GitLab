package com.devcoop.kiosk.domain.item.presentation.dto;

import lombok.Builder;

@Builder
public record EventItemResponse(
        String itemCode,  // barcode를 itemCode로 수정
        String itemName,
        int itemPrice,
        String event, // EventType 대신 String 타입 사용
        String image // 이미지 경로
) {

}
