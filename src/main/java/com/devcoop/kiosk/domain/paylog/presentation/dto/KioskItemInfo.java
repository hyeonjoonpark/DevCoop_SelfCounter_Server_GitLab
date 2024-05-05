package com.devcoop.kiosk.domain.paylog.presentation.dto;

import lombok.Builder;

@Builder
public record KioskItemInfo(
  String itemName,
  int dcmSaleAmt,
  int saleQty
) {

}
