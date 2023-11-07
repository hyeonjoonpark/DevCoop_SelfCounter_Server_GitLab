package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.entity.InventoryEntity;
import com.devcoop.kiosk.domain.entity.ItemEntity;
import com.devcoop.kiosk.domain.presentation.dto.ItemResponseDto;
import com.devcoop.kiosk.domain.repository.InventoryRepository;
import com.devcoop.kiosk.domain.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kiosk")
public class ItemSelectController {

    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;

    public ItemSelectController(ItemRepository itemRepository, InventoryRepository inventoryRepository) {
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @PutMapping("/itemSelect")
    public ResponseEntity<List<ItemResponseDto>> updateItemsByBarcodes(@RequestBody BarcodeRequest barcodeRequest) {
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        try {
            for (String barcode : barcodeRequest.getBarcodes()) {
                ItemEntity item = itemRepository.findByBarcode(barcode);
                if (item != null) {
                    InventoryEntity inventory = inventoryRepository.findByItemId(item.getItemId());
                    if (inventory != null) {
                        int newQuantity = inventory.getQuantity() - 1;
                        inventory.setQuantity(newQuantity);
                        inventoryRepository.save(inventory);
                    }
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

class BarcodeRequest {
    private List<String> barcodes;

    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }
}
