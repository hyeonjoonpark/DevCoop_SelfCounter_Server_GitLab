package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import com.devcoop.kiosk.domain.user.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.user.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KioskService {

    private final PayLogRepository payLogRepository;
    private final UserRepository userRepository;
    private final KioskReceiptRepository kioskReceiptRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Object deductPoints(UserPointRequestDto userPointRequestDto) {
        User user = userRepository.findByCodeNumber(userPointRequestDto.getCodeNumber());

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

//            payLog.setCodeNumber(payLogRequestDto.getCodeNumber());
//            payLog.setDate(ZonedDateTime.now());
//            payLog.setInnerPoint(payLogRequestDto.getInnerPoint());
//
//            User user = userRepository.findPointByCodeNumber(payLogRequestDto.getCodeNumber());
//            payLog.setPoint(user.getPoint() - payLogRequestDto.getInnerPoint());
//
//            payLog.setChargerId("kiosk");
//            payLog.setVerifyKey("test");
//            payLog.setStudentName(payLogRequestDto.getStudentName());
//
//            payLogRepository.save(payLog);
//            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> saveReceipt(KioskDto kioskDto) {
        try {

            kioskReceipt.setDcmSaleAmt(kioskDto.getDcmSaleAmt());

            List<Item> items = itemRepository.findItemEntitiesByItemName(kioskDto.getItemName());
            if (!items.isEmpty()) {
                Item item = items.get(0);
                String itemId = String.valueOf(item.getItemId());
                kioskReceipt.setItemId(itemId);
            } else {
                return ResponseEntity.badRequest().body("Item not found");
            }

            kioskReceipt.setSaleYn(ReceiptType.Y);

            try {
                kioskReceipt.setUserId(Integer.parseInt(kioskDto.getUserId()));
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid userId format");
            }

            kioskReceipt.setItemName(kioskDto.getItemName());
            kioskReceipt.setSaleQty((short) kioskDto.getSaleQty());
            kioskReceipt.setDate(LocalDate.now());

            kioskReceiptRepository.save(kioskReceipt);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
