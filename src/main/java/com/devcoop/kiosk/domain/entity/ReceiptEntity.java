package com.devcoop.kiosk.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "receipt")
@Table(name = "receipt")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptEntity {
    @Id
    @Column(name = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int number;
    @Column(name = "dcm_sale_amt")
    private int dcmSaleAmt;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "sale_yn")
    private Character saleYn;
    @Column(name = "bill_num")
    private int billNum;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "sale_qty")
    private Short saleQty;
    @Column(name = "date")
    private LocalDate date;
}
