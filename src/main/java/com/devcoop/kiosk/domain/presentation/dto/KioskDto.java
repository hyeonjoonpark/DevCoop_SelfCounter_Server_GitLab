package com.devcoop.kiosk.domain.presentation.dto;

import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class KioskDto {
    private int dcmSaleAmt;
    private int itemId;
    private ReceiptType saleYn;
    private int billNum;
    private String itemName;
    private Short saleQty;
    private LocalDate date;
}
