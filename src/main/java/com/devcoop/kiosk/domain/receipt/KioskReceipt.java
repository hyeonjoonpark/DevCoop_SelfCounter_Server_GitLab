package com.devcoop.kiosk.domain.receipt;

import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "kiosk_receipts")
@Getter @ToString
@NoArgsConstructor
public class KioskReceipt {
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
    @Column(name = "user_id") // 영수증 번호
    private int userId;
    @Column(name = "item_name") // 아이템 이름
    private String itemName;
    @Column(name = "sale_qty") // 팔린 양
    private Short saleQty;
    @Column(name = "date") // 팔린 날짜
    private LocalDate date;

    @Builder
    public KioskReceipt(int number, int dcmSaleAmt, String itemId, ReceiptType saleYn, int userId, String itemName, Short saleQty, LocalDate date) {
        this.number = number;
        this.dcmSaleAmt = dcmSaleAmt;
        this.itemId = itemId;
        this.saleYn = saleYn;
        this.userId = userId;
        this.itemName = itemName;
        this.saleQty = saleQty;
        this.date = date;
    }
}
