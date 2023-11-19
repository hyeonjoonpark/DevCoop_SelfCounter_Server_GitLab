package com.devcoop.kiosk.domain.presentation.dto;

import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private ReceiptType saleYn;
    private int billNum;
    private String itemName;
    private Short saleQty;
    private LocalDate date;
}
