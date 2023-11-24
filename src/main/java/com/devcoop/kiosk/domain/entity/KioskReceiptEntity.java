package com.devcoop.kiosk.domain.entity;

import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity(name = "kiosk_receipts")
@Table(name = "kiosk_receipts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class KioskReceiptEntity {
    @Id
    @Column(name = "number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    @Column(name = "dcm_sale_amt") // 팔린 금액
    private int dcmSaleAmt;
    @Column(name = "item_id") // 아이템 Id
    private String itemId;
    @Column(name = "sale_yn") // 팔렸으면 Y 안 팔리면 N
    @Enumerated(EnumType.STRING)
    private ReceiptType saleYn;
    @Column(name = "user_id") // 바코드 번호
    private String userId;
    @Column(name = "item_name") // 아이템 이름
    private String itemName;
    @Column(name = "sale_qty") // 팔린 양
    private Short saleQty;
    @Column(name = "date") // 팔린 날짜
    private LocalDate date;
}
