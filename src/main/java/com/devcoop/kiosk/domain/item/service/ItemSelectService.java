package com.devcoop.kiosk.domain.item.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.presentation.dto.ItemResponse;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<ItemResponse> get(List<String> itemCodes) throws GlobalException {
        List<ItemResponse> itemResponses = new ArrayList<>();

        for (String itemCode : itemCodes) {
            log.info("Service에서 itemCode = {}", itemCode);
            Item item = itemRepository.findByItemCode(itemCode);
            log.info("item = {}", item);

            if (item == null) {
                throw new GlobalException(ErrorCode.BARCODE_NOT_VALID);
            }

            int quantity = 1;
            String eventStatus = "NONE";

            // 이벤트에 따른 수량 및 상태 처리 로직
            if ("ONE_PLUS_ONE".equals(item.getEvent())) {
                quantity = 2;
                eventStatus = "ONE_PLUS_ONE";
            }

            ItemResponse itemResponse = ItemResponse.builder()
                    .itemName(item.getItemName())  // 필드 이름 일치
                    .itemPrice(item.getItemPrice())  // 필드 이름 일치
                    .quantity(quantity)
                    .eventStatus(eventStatus)  // String 타입으로 변경된 필드
                    .build();

            itemResponses.add(itemResponse);
        }
        log.info("{}",itemResponses);
        return itemResponses;
    }
}
