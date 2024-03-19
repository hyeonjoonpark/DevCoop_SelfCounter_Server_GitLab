package com.devcoop.kiosk.domain.receipt.service;

import com.devcoop.kiosk.domain.item.Item;
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
    List<Item> items = itemRepository.findItemEntitiesByItemName(kioskRequest.itemName());

    log.info("items = {}", items);

    if (!items.isEmpty()) {
      Item item = items.get(0);
      String itemId = String.valueOf(item.getItemId());

      KioskReceipt kioskReceipt = kioskRequest.toEntity(itemId);
      System.out.println("kioskReceipt = " + kioskReceipt);
      kioskReceiptRepository.save(kioskReceipt);
    }
    return ResponseEntity.ok().build();
  }
}
