package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.entity.ItemEntity;
import com.devcoop.kiosk.domain.presentation.dto.ItemResponseDto;
import com.devcoop.kiosk.domain.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kiosk")
public class ItemSelectController {

    private final ItemRepository itemRepository;

    public ItemSelectController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/itemSelect")
    public ResponseEntity<List<ItemResponseDto>> getItemsByBarcodes(@RequestParam List<String> barcodes) {
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        for (String barcode : barcodes) {
            ItemEntity item = itemRepository.findByBarcode(barcode);

            if (item != null) {
                ItemResponseDto itemResponse = new ItemResponseDto(item.getItemName(), item.getItemPrice());
                itemResponseDtos.add(itemResponse);
            }
        }

        return ResponseEntity.ok(itemResponseDtos);
    }
}
