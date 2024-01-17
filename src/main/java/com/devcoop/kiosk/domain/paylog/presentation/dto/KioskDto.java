package com.devcoop.kiosk.domain.paylog.presentation.dto;

import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
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
    private short saleQty;
    private LocalDate date;

    public KioskReceipt toEntity(String itemId) {
        return KioskReceipt.builder()
                .dcmSaleAmt(dcmSaleAmt)
                .itemId(String.valueOf(itemId))
                .saleYn(ReceiptType.Y)
                .userId(Integer.parseInt(userId))
                .itemName(itemName)
                .saleQty(saleQty)
                .date(LocalDate.now())
                .build();
    }
}
