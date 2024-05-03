package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

// @Builder -> @SuperBuilder로 변경
@Builder
public record KioskRequest(
  @NotBlank(message = "아이템 정보는 필수값입니다")
  List<KioskItemInfo> items,
  @NotBlank(message = "사용자 아이디는 필수 입력사항입니다")
  String userId
) {

  // 기본 생성자에서 items를 ArrayList로 초기화
  public KioskRequest {
    items = (items == null) ? List.of() : items;
  }

  public List<KioskReceipt> toEntity() {
    return items.stream()
      .map(
        item -> KioskReceipt.builder()
          .dcmSaleAmt(item.dcmSaleAmt())
          .itemName(item.itemName())
          .saleYn(ReceiptType.Y)
          .userId(userId)
          .saleQty((short) item.saleQty())
          .build()
      ).collect(Collectors.toList());
  }
}
