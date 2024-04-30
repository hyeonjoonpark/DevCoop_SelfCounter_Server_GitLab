package com.devcoop.kiosk.domain.item.presentation.dto;

import lombok.Builder;

@Builder
public record ItemResponse(
  String name,
  int price
) {

}
