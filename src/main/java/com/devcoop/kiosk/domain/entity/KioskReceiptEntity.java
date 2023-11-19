package com.devcoop.kiosk.domain.entity;

import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity(name = "KioskReceipts")
@Table(name = "KioskReceipts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class KioskReceiptEntity {
    @Id
    @Column(name = "number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    @Column(name = "dcm_sale_amt")
    private int dcmSaleAmt;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "sale_yn")
    @Enumerated(EnumType.STRING)
    private ReceiptType saleYn;
    @Column(name = "bill_num")
    private int billNum;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "sale_qty")
    private Short saleQty;
    @Column(name = "date")
    private LocalDate date;
}
