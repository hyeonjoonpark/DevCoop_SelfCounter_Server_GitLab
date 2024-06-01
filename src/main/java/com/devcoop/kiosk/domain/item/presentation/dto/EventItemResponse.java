package com.devcoop.kiosk.domain.item.presentation.dto;

import com.devcoop.kiosk.domain.item.types.EventType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EventItemResponse(
  String itemName,
  int itemPrice,
  EventType event,
  LocalDate eventStartDate,
  LocalDate eventEndDate
) {

}
