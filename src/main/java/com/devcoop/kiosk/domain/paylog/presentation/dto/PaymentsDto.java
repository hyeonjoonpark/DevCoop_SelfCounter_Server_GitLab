package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsDto {
//  private UserPointRequestDto userPointRequestDto;
//  private PayLogRequestDto payLogRequestDto;
  private UserPointRequest userPointRequest;
  private PayLogRequest payLogRequest;
  private KioskRequest kioskRequest;
}
