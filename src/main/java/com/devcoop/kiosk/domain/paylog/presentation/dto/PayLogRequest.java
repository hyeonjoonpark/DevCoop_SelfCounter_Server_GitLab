package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.paylog.PayLog;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PayLogRequest(
        @NotBlank(message = "바코드는 필수 입력사항 입니다")
        String userCode, // codeNumber를 userCode로 변경
        int beforePoint, // innerPoint를 beforePoint로 변경
        String managedEmail // studentName을 managedEmail로 변경
) {

    public PayLog toEntity(int afterPoint) { // point를 afterPoint로 변경
        return PayLog.builder()
                .userCode(userCode) // codeNumber -> userCode
                .beforePoint(beforePoint) // innerPoint -> beforePoint
                .payedPoint(afterPoint - beforePoint) // 결제된 포인트 계산
                .afterPoint(afterPoint) // 최종 포인트
                .managedEmail(managedEmail) // studentName -> managedEmail
                .payType("1") // payType을 "1"로 설정 (예시 값)
                .eventType((short) 1) // 이벤트 유형 설정 (예시 값)
                .build();
    }
}
