package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponseDto;
import com.devcoop.kiosk.domain.item.service.ItemSelectService;
import com.devcoop.kiosk.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class SelectItemController {
  private final ItemSelectService itemSelectService;
  @GetMapping("/itemSelect")
  public List<ItemResponseDto> getItemByBarcode(@RequestParam List<String> barcodes) throws GlobalException {
    log.info("barcodes = {}", barcodes.toString());
    List<ItemResponseDto> itemResponseDtos = itemSelectService.get(barcodes);
    return itemResponseDtos;
  }
}
