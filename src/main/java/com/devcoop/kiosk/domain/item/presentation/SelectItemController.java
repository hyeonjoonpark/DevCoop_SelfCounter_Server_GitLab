package com.devcoop.kiosk.domain.item.presentation;

import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponse;
import com.devcoop.kiosk.domain.item.service.ItemSelectService;
import com.devcoop.kiosk.global.exception.GlobalException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j @Tag(name = "item", description = "Item API")
public class SelectItemController {
  private final ItemSelectService itemSelectService;
  @GetMapping("/itemSelect")
  @Operation(summary = "get item info", description = "아이템 정보 선택")
  public List<ItemResponse> getItemByBarcode(@RequestParam List<String> barcodes) throws GlobalException {
    log.info("barcodes = {}", barcodes.toString());
    List<ItemResponse> itemResponses = itemSelectService.get(barcodes);
    return itemResponses;
  }
}
