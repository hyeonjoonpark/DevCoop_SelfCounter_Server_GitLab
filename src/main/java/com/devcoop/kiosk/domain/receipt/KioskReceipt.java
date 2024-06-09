package com.devcoop.kiosk.domain.receipt;

import com.devcoop.kiosk.domain.item.types.EventType;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "kiosk_receipts")
@Getter
@ToString
@NoArgsConstructor
@Check(constraints = "event_type IN ('ONE_PLUS_ONE', 'NONE')")
public class KioskReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private int dcmSaleAmt;// 팔린 금액
    @Setter
    private String itemId;// 아이템 Id

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReceiptType saleYn;// 팔렸으면 Y 안 팔리면 N
    private String userId;// 영수증 번호
    private String itemName; // 아이템 이름
    private Short saleQty; // 팔린 양
    @CreationTimestamp
    private LocalDateTime date = LocalDateTime.now(); // 팔린 날짜
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType = EventType.NONE;

    @Builder
    public KioskReceipt(int number, int dcmSaleAmt, String itemId, ReceiptType saleYn, String userId, String itemName, Short saleQty, LocalDateTime date, EventType eventType) {
        this.number = number;
        this.dcmSaleAmt = dcmSaleAmt;
        this.itemId = itemId;
        this.saleYn = saleYn;
        this.userId = userId;
        this.itemName = itemName;
        this.saleQty = saleQty;
        this.date = date;
        this.eventType = eventType;
    }
}
