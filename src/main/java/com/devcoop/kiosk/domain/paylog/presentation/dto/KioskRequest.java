package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record KioskRequest(
  List<KioskItemInfo> items,
  String userId
) {

}
