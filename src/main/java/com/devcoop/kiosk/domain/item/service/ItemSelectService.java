package com.devcoop.kiosk.domain.item.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponse;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.item.types.EventType;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
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
    private final KioskReceiptRepository kioskReceiptRepository;

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

            int quantity = 1;
            EventType eventStatus = EventType.NONE;

            if (item.getEvent().equals(EventType.ONE_PLUS_ONE)) {
                quantity = 2;
                eventStatus = EventType.ONE_PLUS_ONE;
            }

            ItemResponse itemResponse = ItemResponse.builder()
                    .name(item.getItemName())
                    .price(item.getItemPrice())
                    .quantity(quantity)
                    .eventStatus(eventStatus)
                    .build();

            itemResponses.add(itemResponse);
        }

        return itemResponses;
    }
}
