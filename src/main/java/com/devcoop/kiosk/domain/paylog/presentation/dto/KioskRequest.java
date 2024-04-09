package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

public record KioskRequest(
  int dcmSaleAmt,
  int itemId,
  ReceiptType saleYn,
  @NotBlank(message = "사용자 아이디는 필수 입력사항입니다")
  String userId,
  String itemName,
  short saleQty,
  LocalDate date
) {

  @Builder
  public KioskRequest(int dcmSaleAmt, int itemId, ReceiptType saleYn, String userId, String itemName, short saleQty, LocalDate date) {
    this.dcmSaleAmt = dcmSaleAmt;
    this.itemId = itemId;
    this.saleYn = saleYn;
    this.userId = userId;
    this.itemName = itemName;
    this.saleQty = saleQty;
    this.date = date;
  }

  public KioskReceipt toEntity(Item item) {
    return KioskReceipt.builder()
      .dcmSaleAmt(dcmSaleAmt)
      .itemName(item.getItemName())
      .saleYn(ReceiptType.Y)
      .userId(userId)
      .itemName(itemName)
      .saleQty(saleQty)
      .date(LocalDate.now())
      .build();
  }
}
