package com.devcoop.kiosk.domain.receipt;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "occount_kioskReceipts") // 테이블 이름 변경
@Getter
@ToString
@NoArgsConstructor
public class KioskReceipt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiptId; // 거래식별용 id
    
    private String itemCode; // 품목 id
    
    private int tradedPoint; // 거래 가격
    
    private String itemName; // 품목 이름
    
    private int saleQty; // 품목 수량
    
    @CreationTimestamp
    private LocalDateTime saleDate = LocalDateTime.now(); // 거래 발생 시간
    
    private String userCode; // 사용자 바코드
    
    private String saleType; // 결제 타입
    
    private String eventType; // 이벤트 여부 ('ONE_PLUS_ONE', 'NONE' 등)

    @Builder
    public KioskReceipt(int receiptId, String itemCode, int tradedPoint, String itemName, int saleQty, LocalDateTime saleDate, String userCode, String saleType, String eventType) {
        this.receiptId = receiptId;
        this.itemCode = itemCode;
        this.tradedPoint = tradedPoint;
        this.itemName = itemName;
        this.saleQty = saleQty;
        this.saleDate = saleDate;
        this.userCode = userCode;
        this.saleType = saleType;
        this.eventType = eventType;
    }
}
