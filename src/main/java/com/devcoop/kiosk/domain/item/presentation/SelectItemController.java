package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponseDto;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SelectItemController {
  private final ItemRepository itemRepository;

  @GetMapping("/itemSelect")
  public ResponseEntity<List<ItemResponseDto>> getItemsByBarcodes(@RequestParam List<String> barcodes) {
    List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
    System.out.println("요청 성공");

    try {
      for (String barcode : barcodes) {
        System.out.println("barcode = " + barcode);
        Item item = itemRepository.findByBarcode(barcode);
        System.out.println(item);
        if (item != null) {
          ItemResponseDto itemResponse = new ItemResponseDto(item.getItemName(), item.getItemPrice(), item.getBarcode());
          System.out.println(itemResponse);
          itemResponseDtos.add(itemResponse);
        }
      }

      return ResponseEntity.ok(itemResponseDtos);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }
}
