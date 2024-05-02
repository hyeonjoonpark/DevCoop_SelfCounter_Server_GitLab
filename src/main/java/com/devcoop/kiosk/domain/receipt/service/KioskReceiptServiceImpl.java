package com.devcoop.kiosk.domain.receipt.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskItemInfo;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KioskReceiptServiceImpl implements ReceiptService {
  private final KioskReceiptRepository kioskReceiptRepository;
  private final ItemRepository itemRepository;

  @Override
  public ResponseEntity<Object> saveReceipt(KioskRequest kioskRequest) {
    // KioskRequest에서 모든 items를 순회합니다.
    for (KioskItemInfo itemInfo : kioskRequest.items()) {
      // 각 아이템의 이름으로 Item 객체를 조회합니다.
      List<Item> items = itemRepository.findItemEntitiesByItemName(itemInfo.itemName());

      log.info("items = {}", items);

      if (!items.isEmpty()) {
        Item item = items.get(0);
        String itemId = String.valueOf(item.getItemId());

        // KioskReceipt 객체를 생성하는 로직을 반복문 내부에서 처리합니다.
        // 여기서는 toEntity 메소드를 수정하거나, 새로운 방식으로 KioskReceipt를 생성해야 합니다.
        // 아래 코드는 수정이 필요할 수 있습니다.
        List<KioskReceipt> kioskReceipts = kioskRequest.toEntity(); // 이 부분이 수정되어야 함
        for (KioskReceipt kioskReceipt : kioskReceipts) {
          System.out.println("kioskReceipt = " + kioskReceipt);
          kioskReceiptRepository.save(kioskReceipt);
        }
      }
    }
    return ResponseEntity.ok().build();
  }

}
