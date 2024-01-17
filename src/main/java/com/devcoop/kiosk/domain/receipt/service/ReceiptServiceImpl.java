package com.devcoop.kiosk.domain.receipt.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskDto;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final KioskReceiptRepository kioskReceiptRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ReceiptServiceImpl(KioskReceiptRepository kioskReceiptRepository, ItemRepository itemRepository) {
        this.kioskReceiptRepository = kioskReceiptRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ResponseEntity<Object> saveReceipt(KioskDto kioskDto) {
        List<Item> items = itemRepository.findItemEntitiesByItemName(kioskDto.getItemName());
        if (!items.isEmpty()) {
            Item item = items.get(0);
            String itemId = String.valueOf(item.getItemId());

            KioskReceipt kioskReceipt = kioskDto.toEntity(itemId);
            kioskReceiptRepository.save(kioskReceipt);
        }
        return ResponseEntity.ok().build();
    }
}
