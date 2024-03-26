package com.devcoop.kiosk.domain.item.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponse;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemSelectService {

  private final ItemRepository itemRepository;

  @Transactional(readOnly = true)
  public List<ItemResponse> get(List<String> barcodes) throws GlobalException {
    List<ItemResponse> itemResponses = new ArrayList<>();

    for (String barcode : barcodes) {
      log.info("service에서 barcode = {}", barcode);
      Item item = itemRepository.findByBarcode(barcode);
      log.info("item = {}", item);

      if (item == null) {
        throw new GlobalException(ErrorCode.BARCODE_NOT_VALID);
      }

      ItemResponse itemResponse = new ItemResponse(
        item.getItemName(),
        item.getItemPrice()
      );

      itemResponses.add(itemResponse);
    }

    return itemResponses;
  }
}
