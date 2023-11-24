package com.devcoop.kiosk.domain.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {


    private String requestTimeUtc; // 추가: 요청 시간을 UTC로 저장
    private String token;
    // 다른 필요한 필드들을 추가할 수 있음

    // 생성자, 게터, 세터 등도 자동으로 생성됨
}
