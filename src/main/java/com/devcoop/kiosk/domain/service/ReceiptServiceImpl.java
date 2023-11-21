package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.repository.KioskReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final KioskReceiptRepository kioskReceiptRepository;

    @Autowired
    public ReceiptServiceImpl(KioskReceiptRepository kioskReceiptRepository) {
        this.kioskReceiptRepository = kioskReceiptRepository;
    }

    @Override
    public ResponseEntity<Object> saveReceipt(KioskDto kioskDto) {
        try {
            // 새로운 엔터티 생성
            KioskReceiptEntity kioskReceiptEntity = new KioskReceiptEntity();

            kioskReceiptEntity.setDcmSaleAmt(kioskDto.getDcmSaleAmt());
            kioskReceiptEntity.setItemId(kioskDto.getItemId());
            kioskReceiptEntity.setSaleYn(kioskDto.getSaleYn());
            kioskReceiptEntity.setUserId(kioskDto.getUserId());
            kioskReceiptEntity.setItemName(kioskDto.getItemName());
            kioskReceiptEntity.setSaleQty(kioskDto.getSaleQty());
            kioskReceiptEntity.setDate(LocalDate.now());

            kioskReceiptRepository.save(kioskReceiptEntity);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}