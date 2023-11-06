package com.devcoop.kiosk.global.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // User
    USER_NOT_FOUND("찾을 수 없는 사용자입니다", 404),

    // Server
    INTERNAL_SERVER_ERROR("서버 에러가 발생하였습니다", 500),

    // Auth
    BARCODE_NOT_VALID("존재하지 않는 바코드입니다", 404);

    private final String message;
    private final int status;
}
