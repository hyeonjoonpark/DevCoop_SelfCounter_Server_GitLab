package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record KioskRequest(
  int dcmSaleAmt,
  int itemId,
  ReceiptType saleYn,
  @NotBlank(message = "사용자 아이디는 필수 입력사항입니다")
  String userId,
  String itemName,
  short saleQty,
  LocalDateTime date
) {

  @Builder
  public KioskRequest(int dcmSaleAmt, int itemId, ReceiptType saleYn, String userId, String itemName, short saleQty, LocalDateTime date) {
    this.dcmSaleAmt = dcmSaleAmt;
    this.itemId = itemId;
    this.saleYn = saleYn;
    this.userId = userId;
    this.itemName = itemName;
    this.saleQty = saleQty;
    this.date = date;
  }

  public KioskReceipt toEntity(String itemId) {
    return KioskReceipt.builder()
      .dcmSaleAmt(dcmSaleAmt)
      .itemName(itemId)
      .saleYn(ReceiptType.Y)
      .userId(userId)
      .itemName(itemName)
      .saleQty(saleQty)
      .date(LocalDateTime.now())
      .build();
  }
}
