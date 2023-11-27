package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.ItemEntity;
import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import com.devcoop.kiosk.domain.entity.PayLogEntity;
import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.repository.ItemRepository;
import com.devcoop.kiosk.domain.repository.KioskReceiptRepository;
import com.devcoop.kiosk.domain.repository.PayLogRepository;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class KioskService {

    private final PayLogRepository payLogRepository;
    private final UserRepository userRepository;
    private final KioskReceiptRepository kioskReceiptRepository;
    private final ItemRepository itemRepository;

    private final KioskReceiptEntity kioskReceiptEntity;
    private final PayLogEntity payLogEntity;

    @Autowired
    public KioskService(PayLogRepository payLogRepository, UserRepository userRepository, KioskReceiptRepository kioskReceiptRepository, ItemRepository itemRepository, KioskReceiptEntity kioskReceiptEntity, PayLogEntity payLogEntity) {
        this.payLogRepository = payLogRepository;
        this.userRepository = userRepository;
        this.kioskReceiptRepository = kioskReceiptRepository;
        this.itemRepository = itemRepository;
        this.kioskReceiptEntity = kioskReceiptEntity;
        this.payLogEntity = payLogEntity;
    }

    @Transactional
    public Object deductPoints(UserPointRequestDto userPointRequestDto) {
        UserEntity user = userRepository.findByCodeNumber(userPointRequestDto.getCodeNumber());

        try {
            if (user != null && user.getPoint() >= userPointRequestDto.getTotalPrice()) {
                int newPoint = user.getPoint() - userPointRequestDto.getTotalPrice();
                user.setPoint(newPoint);
                userRepository.save(user);

                return newPoint;
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> savePayLog(PayLogRequestDto payLogRequestDto) {
        try {
            payLogEntity.setCodeNumber(payLogRequestDto.getCodeNumber());
            payLogEntity.setDate(ZonedDateTime.now());
            payLogEntity.setInnerPoint(payLogRequestDto.getInnerPoint());

            UserEntity user = userRepository.findPointByCodeNumber(payLogRequestDto.getCodeNumber());
            payLogEntity.setPoint(user.getPoint() - payLogRequestDto.getInnerPoint());

            payLogEntity.setChargerId("kiosk");
            payLogEntity.setVerifyKey("test");
            payLogEntity.setStudentName(payLogRequestDto.getStudentName());

            payLogRepository.save(payLogEntity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> saveReceipt(KioskDto kioskDto) {
        try {

            kioskReceiptEntity.setDcmSaleAmt(kioskDto.getDcmSaleAmt());

            List<ItemEntity> items = itemRepository.findItemEntitiesByItemName(kioskDto.getItemName());
            if (!items.isEmpty()) {
                ItemEntity item = items.get(0);
                String itemId = String.valueOf(item.getItemId());
                kioskReceiptEntity.setItemId(itemId);
            } else {
                return ResponseEntity.badRequest().body("Item not found");
            }

            kioskReceiptEntity.setSaleYn(ReceiptType.Y);

            try {
                kioskReceiptEntity.setUserId(Integer.parseInt(kioskDto.getUserId()));
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid userId format");
            }

            kioskReceiptEntity.setItemName(kioskDto.getItemName());
            kioskReceiptEntity.setSaleQty((short) kioskDto.getSaleQty());
            kioskReceiptEntity.setDate(LocalDate.now());

            kioskReceiptRepository.save(kioskReceiptEntity);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
