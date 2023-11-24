package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.ItemEntity;
import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.repository.ItemRepository;
import com.devcoop.kiosk.domain.repository.KioskReceiptRepository;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final KioskReceiptRepository kioskReceiptRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReceiptServiceImpl(KioskReceiptRepository kioskReceiptRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.kioskReceiptRepository = kioskReceiptRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Object> saveReceipt(KioskDto kioskDto) {
        try {
            // 새로운 엔터티 생성
            KioskReceiptEntity kioskReceiptEntity = new KioskReceiptEntity();

            kioskReceiptEntity.setDcmSaleAmt(kioskDto.getDcmSaleAmt());
//            user = userRepository.findByCodeNumber(kioskDto.)
//            kioskReceiptEntity.setUserId();
            List<ItemEntity> items = itemRepository.findItemEntitiesByItemName(kioskDto.getItemName());
            if (!items.isEmpty()) {
                ItemEntity item = items.get(0);
                String itemId = String.valueOf(item.getItemId());
                kioskReceiptEntity.setItemId(itemId);
            } else {
                // 아이템이 없는 경우에 대한 처리 로직 추가
                return ResponseEntity.badRequest().body("Item not found");
            }

            // ReceiptType의 값을 사용하도록 수정
            kioskReceiptEntity.setSaleYn(ReceiptType.Y);

            // 예외 처리를 추가하여 안전하게 UserId를 파싱
            try {
                int userId = Integer.parseInt(kioskDto.getUserId());
                kioskReceiptEntity.setUserId(String.valueOf(userId));
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid userId format");
            }

            kioskReceiptEntity.setItemName(kioskDto.getItemName());
            kioskReceiptEntity.setSaleQty((short) kioskDto.getSaleQty());
            kioskReceiptEntity.setDate(LocalDate.now());

            // 트랜잭션 내에서 수행되도록 수정
            kioskReceiptRepository.save(kioskReceiptEntity);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 예외 로그 출력
            e.printStackTrace();
            // 예외 처리를 추가하여 내부 서버 오류 응답
            return ResponseEntity.internalServerError().build();
        }
    }
}
