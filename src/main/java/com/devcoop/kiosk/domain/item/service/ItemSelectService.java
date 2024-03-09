package com.devcoop.kiosk.domain.item.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponseDto;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSelectService {

  private final ItemRepository itemRepository;

  @Transactional(readOnly = true)
  public List<ItemResponseDto> get(List<String> barcodes) {
    List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

    for (String barcode : barcodes) {
      Item item = itemRepository.findByBarcode(barcode);

      if (item == null) {
        throw new RuntimeException(ErrorCode.BARCODE_NOT_VALID.getMessage());
      }

      ItemResponseDto itemResponseDto = new ItemResponseDto(
        item.getItemName(),
        item.getItemPrice()
      );

      itemResponseDtos.add(itemResponseDto);
    }

    return itemResponseDtos;
  }
}
