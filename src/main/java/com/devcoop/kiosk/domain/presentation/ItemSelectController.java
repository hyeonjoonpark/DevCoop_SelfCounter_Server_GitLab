package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.entity.ItemEntity;
import com.devcoop.kiosk.domain.presentation.dto.ItemResponseDto;
import com.devcoop.kiosk.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kiosk")
public class ItemSelectController {
    @Autowired ItemRepository itemRepository;


    @GetMapping("/itemSelect")
    public ResponseEntity<List<ItemResponseDto>> getItemsByBarcodes(@RequestParam List<String> barcodes) {
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        try {
            for (String barcode : barcodes) {
                ItemEntity item = itemRepository.findByBarcode(barcode);
                if (item != null) {
                    ItemResponseDto itemResponse = new ItemResponseDto(item.getItemName(), item.getItemPrice());
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
