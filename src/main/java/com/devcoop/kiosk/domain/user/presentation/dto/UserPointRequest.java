package com.devcoop.kiosk.domain.user.presentation.dto;

public record UserPointRequest(
  String codeNumber,
  int totalPrice
) {

}
