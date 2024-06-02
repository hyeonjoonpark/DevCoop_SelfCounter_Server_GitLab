package com.devcoop.kiosk.domain.user.types;

import lombok.Getter;

@Getter
public enum Role {
  ROLE_USER_NOT_COOP("일반 사용자 (협동조합 아님)"),
  ROLE_USER_COOP("협동조합 사용자"),
  ROLE_ADMIN("관리자 (매점부원)");

  private final String description;

  Role(String description) {
    this.description = description;
  }
}
