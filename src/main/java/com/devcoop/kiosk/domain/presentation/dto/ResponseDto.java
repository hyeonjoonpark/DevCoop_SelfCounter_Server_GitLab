package com.devcoop.kiosk.domain.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;
    private String token; // 토큰 정보 추가

    public static <D> ResponseDto<D> setSuccess(String message, D data, String token) {
        return ResponseDto.set(true, message, data, token);
    }

    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null, null);
    }
}
