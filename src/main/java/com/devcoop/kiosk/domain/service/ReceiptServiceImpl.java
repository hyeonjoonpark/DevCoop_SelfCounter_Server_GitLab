package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.ItemEntity;
import com.devcoop.kiosk.domain.entity.KioskReceiptEntity;
import com.devcoop.kiosk.domain.entity.enums.ReceiptType;
import com.devcoop.kiosk.domain.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.repository.ItemRepository;
import com.devcoop.kiosk.domain.repository.KioskReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final KioskReceiptRepository kioskReceiptRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ReceiptServiceImpl(KioskReceiptRepository kioskReceiptRepository, ItemRepository itemRepository) {
        this.kioskReceiptRepository = kioskReceiptRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ResponseEntity<Object> saveReceipt(KioskDto kioskDto) {
        try {
            // 새로운 엔터티 생성
            KioskReceiptEntity kioskReceiptEntity = new KioskReceiptEntity();

            kioskReceiptEntity.setDcmSaleAmt(kioskDto.getDcmSaleAmt());

            List<ItemEntity> items = itemRepository.findItemEntitiesByItemName(kioskDto.getItemName());
            if (!items.isEmpty()) {
                ItemEntity item = items.get(0);
                String itemId = String.valueOf(item.getItemId());
                kioskReceiptEntity.setItemId(itemId);
            } else {
                // 아이템이 없는 경우에 대한 처리 로직 추가
                return ResponseEntity.badRequest().body("Item not found");
            }



            // 수정: ReceiptType의 값을 사용하도록 수정
            kioskReceiptEntity.setSaleYn(ReceiptType.Y);

            // 수정: 예외 처리를 추가하여 안전하게 UserId를 파싱
            try {
                kioskReceiptEntity.setUserId(Integer.parseInt(kioskDto.getUserId()));
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid userId format");
            }

            kioskReceiptEntity.setItemName(kioskDto.getItemName());
            kioskReceiptEntity.setSaleQty((short) kioskDto.getSaleQty());
            kioskReceiptEntity.setDate(LocalDate.now());

            // 수정: 트랜잭션 내에서 수행되는지 확인
            kioskReceiptRepository.save(kioskReceiptEntity);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // 수정: 예외 로그 출력
            e.printStackTrace();
            // 수정: 예외 처리를 추가하여 내부 서버 오류 응답
            return ResponseEntity.internalServerError().build();
        }
    }
}
