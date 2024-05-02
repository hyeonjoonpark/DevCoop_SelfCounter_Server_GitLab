package com.devcoop.kiosk.domain.paylog.presentation.dto;

import lombok.Builder;

@Builder
public record KioskItemInfo(
  int dcmSaleAmt,
  String itemName,
  int saleQty
) {

}
