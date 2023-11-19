package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.repository.KioskReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final KioskReceiptEntity kioskReceiptEntity;
    private final KioskReceiptRepository kioskReceiptRepository;

    @Autowired
    public ReceiptServiceImpl(KioskReceiptEntity kioskReceiptEntity, KioskReceiptRepository kioskReceiptRepository) {
        this.kioskReceiptEntity = kioskReceiptEntity;
        this.kioskReceiptRepository = kioskReceiptRepository;
    }

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
