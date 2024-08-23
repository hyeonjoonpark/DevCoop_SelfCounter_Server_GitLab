package com.devcoop.kiosk.domain.paylog;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "occount_payLog") // 테이블 이름을 새로운 테이블 이름으로 변경
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payId; // 결제기록id, 기존의 payNum을 payId로 변경
    
    private String userCode; // 기존 codeNumber를 userCode로 변경
    
    @CreationTimestamp
    private LocalDateTime payDate; // 결제 날짜, 기존 date를 payDate로 변경
    
    private String payType; // 결제유형, 기존 type을 payType으로 변경하고 Short에서 String으로 변경
    
    private int beforePoint; // 결제 전 금액, 기존 innerPoint를 beforePoint로 변경
    
    private int payedPoint; // 결제한 금액, 기존 point를 payedPoint로 변경
    
    private int afterPoint; // 결제 후 금액 추가
    
    private String managedEmail; // 담당 매점부 이메일, 기존 chargerId를 managedEmail로 변경
    
    private String eventType; // 이벤트 유형, 기존 verifyKey와 studentName을 제거하고 eventType을 추가

    @Builder
    public PayLog(int payId, String userCode, LocalDateTime payDate, String payType, 
                  int beforePoint, int payedPoint, int afterPoint, 
                  String managedEmail, String eventType) {
        this.payId = payId;
        this.userCode = userCode;
        this.payDate = payDate;
        this.payType = payType;
        this.beforePoint = beforePoint;
        this.payedPoint = payedPoint;
        this.afterPoint = afterPoint;
        this.managedEmail = managedEmail;
        this.eventType = eventType;
    }
}
