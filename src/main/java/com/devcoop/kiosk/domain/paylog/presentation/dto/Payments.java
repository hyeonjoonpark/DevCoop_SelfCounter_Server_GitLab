package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;

public record Payments(
  UserPointRequest userPointRequest,
  PayLogRequest payLogRequest,
  KioskRequest kioskRequest
) {
}
