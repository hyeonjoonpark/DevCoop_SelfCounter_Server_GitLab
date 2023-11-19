package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.repository.KioskReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired KioskReceiptEntity kioskReceiptEntity;
    @Autowired KioskReceiptRepository kioskReceiptRepository;
    @Override
    public void saveReceipt(KioskDto kioskDto) {
        kioskReceiptEntity.setDcmSaleAmt(kioskDto.getDcmSaleAmt());
        kioskReceiptEntity.setItemId(kioskDto.getItemId());
        kioskReceiptEntity.setSaleYn(kioskDto.getSaleYn());
        kioskReceiptEntity.setBillNum(kioskDto.getBillNum());
        kioskReceiptEntity.setItemName(kioskDto.getItemName());
        kioskReceiptEntity.setSaleQty(kioskDto.getSaleQty());
        kioskReceiptEntity.setDate(LocalDate.now());

        kioskReceiptRepository.save(kioskReceiptEntity);
    }
}
