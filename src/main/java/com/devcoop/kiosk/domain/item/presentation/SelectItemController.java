package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponseDto;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.item.service.ItemSelectService;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SelectItemController {
  private final ItemSelectService itemSelectService;

  @GetMapping("/itemSelect")
  public List<ItemResponseDto> getItemsByBarcodes(@RequestParam List<String> barcodes) {
    List<ItemResponseDto> items = itemSelectService.get(barcodes);
    return items;
  }
}
