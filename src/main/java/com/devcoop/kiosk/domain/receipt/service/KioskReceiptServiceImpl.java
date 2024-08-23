package com.devcoop.kiosk.domain.receipt.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskItemInfo;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
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

            // 이벤트 타입 설정
            String eventType = "NONE";
            if (item.getEvent().equals("ONE_PLUS_ONE")) {
                eventType = "ONE_PLUS_ONE";
            }

            // KioskReceipt 객체 생성 및 데이터베이스 저장
            KioskReceipt kioskReceipt = KioskReceipt.builder()
                .tradedPoint(itemInfo.dcmSaleAmt()) // 거래 금액 설정
                .itemName(item.getItemName()) // 품목 이름 설정
                .saleQty(itemInfo.saleQty()) // 품목 수량 설정
                .userCode(kioskRequest.getUserId()) // 사용자 바코드 설정
                .itemCode(String.valueOf(item.getItemId())) // 품목 코드 설정
                .saleType((byte) 0) // 결제 타입 설정 (0: 정상 결제)
                .eventType(eventType) // 이벤트 타입 설정
                .build();
            System.out.println("kioskReceipt = " + kioskReceipt);
    

            kioskReceiptRepository.save(kioskReceipt); // 데이터베이스에 저장
        }

        return ResponseEntity.ok().build(); // 응답 반환
    }
}
