package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record KioskRequest(
  List<KioskItemInfo> items,
  @NotBlank(message = "사용자 아이디는 필수 입력사항입니다")
  String userId
) {

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
