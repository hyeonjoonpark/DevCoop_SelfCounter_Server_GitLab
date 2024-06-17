package com.devcoop.kiosk.domain.user.types;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER_NOT_COOP("협동조합을 가입하지 않은 학생"),
    ROLE_USER_COOP("협동조합 학생"),
    ROLE_ADMIN("매점부원"),
    ROLE_TEACHER("선생님");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
