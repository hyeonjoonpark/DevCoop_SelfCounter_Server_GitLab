package com.devcoop.kiosk.domain.presentation.dto;

import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KioskDto {
    private int dcmSaleAmt;
    private int itemId;
    private ReceiptType saleYn;
    private String userId;
    private String itemName;
    private int saleQty;
    private LocalDate date;
}
