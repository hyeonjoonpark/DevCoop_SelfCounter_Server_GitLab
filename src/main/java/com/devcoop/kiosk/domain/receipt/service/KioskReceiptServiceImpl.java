// KioskReceiptServiceImpl.java 수정
package com.devcoop.kiosk.domain.receipt.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.item.types.EventType;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskItemInfo;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KioskReceiptServiceImpl implements ReceiptService {
    private final KioskReceiptRepository kioskReceiptRepository;
    private final ItemRepository itemRepository;

    @Override
    public ResponseEntity<Object> save(KioskRequest kioskRequest) {
        List<KioskItemInfo> requestItems = kioskRequest.getItems();
        System.out.println("requestItemList = " + requestItems);
        for (KioskItemInfo itemInfo : requestItems) {
            System.out.println("itemInfo = " + itemInfo);
            Item item = itemRepository.findByItemName(itemInfo.itemName());
            System.out.println("item = " + item);

            if (item == null) {
                throw new NotFoundException("없는 상품입니다");
            }

            EventType eventType = EventType.NONE;

            if (item.getEvent().equals(EventType.NONE)) {
                eventType = EventType.ONE_PLUS_ONE;
            }

            KioskReceipt kioskReceipt = KioskReceipt.builder()
                    .dcmSaleAmt(itemInfo.dcmSaleAmt())
                    .itemName(item.getItemName())
                    .saleQty((short) itemInfo.saleQty())
                    .userId(kioskRequest.getUserId())
                    .itemId(String.valueOf(item.getItemId()))
                    .saleYn(ReceiptType.Y)
                    .eventType(eventType)
                    .build();
            System.out.println("kioskReceipt = " + kioskReceipt);

            kioskReceiptRepository.save(kioskReceipt);
        }

        return ResponseEntity.ok().build();
    }
}
