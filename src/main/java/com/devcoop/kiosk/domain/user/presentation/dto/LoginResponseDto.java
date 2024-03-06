package com.devcoop.kiosk.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDto {
  private String token;
  private int studentNumber;
  private String codeNumber;
  private String studentName;
  private int point;
}
