package com.devcoop.kiosk.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponse {
  private String token;
  private int studentNumber;
  private String codeNumber;
  private String studentName;
  private int point;
}
