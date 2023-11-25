package com.devcoop.kiosk.domain.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;
    private HttpStatus status; // HTTP 상태 코드를 나타내는 필드 추가

    public static <D> ResponseDto<D> setSuccess(String message, D data, HttpStatus ok) {
        return ResponseDto.set(true, message, data, HttpStatus.OK);
    }

    public static <D> ResponseDto<D> setFailed(String message, HttpStatus status) {
        return ResponseDto.set(false, message, null, status);
    }

    // 오류 메시지만을 사용하는 경우
    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null, HttpStatus.BAD_REQUEST);
    }
}
