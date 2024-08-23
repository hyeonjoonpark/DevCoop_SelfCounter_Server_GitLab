package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskItemInfo;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.paylog.presentation.dto.Payments;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SelfCounterService {

    private final PayLogRepository payLogRepository;
    private final UserRepository userRepository;
    private final KioskReceiptRepository kioskReceiptRepository;
    private final ItemRepository itemRepository;

    public int deductPoints(UserPointRequest userPointRequest) {
        log.info("userPointRequest = {}", userPointRequest);
        String userCode = userPointRequest.userCode();
        User user = userRepository.findByUserCode(userCode);

        if (user == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다."); // 이 부분을 더 구체적인 예외 클래스로 변경하는 것을 고려할 수 있습니다.
        }

        if (user.getUserPoint() >= userPointRequest.totalPrice()) {
            int newPoint = user.getUserPoint() - userPointRequest.totalPrice();
            user.setUserPoint(newPoint);
            userRepository.save(user);
            return newPoint; // 새로운 포인트 반환
        } else {
            throw new RuntimeException("결제하는 것에 실패했습니다."); // 이 부분도 커스텀 예외 클래스로 변경을 고려해볼 수 있습니다.
        }
    }

    public void savePayLog(int beforePoint, int afterPoint, PayLogRequest payLogRequest) {
        try {
            // PayLog 엔티티 생성 시, 정확한 beforePoint와 afterPoint를 사용
            PayLog payLog = payLogRequest.toEntity(beforePoint, afterPoint);
            payLogRepository.save(payLog);
        } catch (Exception e) {
            log.error("결제 로그 저장 중 오류가 발생하였습니다.", e);
            throw new RuntimeException("결제 로그 저장 중 오류가 발생하였습니다.", e);
        }
    }
    
    
    

    public void saveReceipt(KioskRequest kioskRequest) {
        try {
            List<KioskItemInfo> requestItems = kioskRequest.getItems();
            log.info("requestItemList = {}", requestItems);
            for (KioskItemInfo itemInfo : requestItems) {
                Item item = itemRepository.findByItemName(itemInfo.itemName());
                log.info("item = {}", item);

                if (item == null) {
                    throw new RuntimeException("없는 상품입니다.");
                }

                String eventType = "NONE";

                if ("ONE_PLUS_ONE".equals(item.getEvent())) {
                    eventType = "ONE_PLUS_ONE";
                }

                KioskReceipt kioskReceipt = KioskReceipt.builder()
                        .tradedPoint(itemInfo.dcmSaleAmt())  // 필드명 변경
                        .itemName(item.getItemName())
                        .saleQty((short) itemInfo.saleQty())
                        .userCode(kioskRequest.getUserId())  // 필드명 변경
                        .itemCode(String.valueOf(item.getItemId()))  // 필드명 변경
                        .saleType((byte) 0)
                        .eventType(eventType)  // eventType을 String으로 처리
                        .build();

                kioskReceiptRepository.save(kioskReceipt);
            }
        } catch (Exception e) {
            log.error("영수증 저장 중 오류가 발생하였습니다.", e);
            throw new RuntimeException("영수증 저장 중 오류가 발생하였습니다.", e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> executeAllTransactions(Payments payments) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 포인트 차감 전 beforePoint를 얻기 위해 User 상태 조회
            User userBeforePayment = userRepository.findByUserCode(payments.payLogRequest().userCode());
            int beforePoint = userBeforePayment.getUserPoint();

            // 포인트 차감
            UserPointRequest userPointRequestDto = payments.userPointRequest();
            int newPoints = deductPoints(userPointRequestDto);
            response.put("remainingPoints", newPoints); // 새로운 포인트 반환

            // afterPoint는 차감된 포인트 값을 사용하여 직접 계산
            int afterPoint = newPoints; // 이미 차감된 후의 포인트가 newPoints에 저장되어 있음

            // 결제 로그 저장 시 정확한 beforePoint와 afterPoint를 전달
            savePayLog(beforePoint, afterPoint, payments.payLogRequest());

            // 영수증 저장
            saveReceipt(payments.kioskRequest());

            response.put("status", "success");
            response.put("message", "결제가 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            log.error("트랜잭션 실패 및 롤백", e);
            throw new RuntimeException(e.getMessage(), e); // 예외를 다시 던져 트랜잭션을 롤백합니다.
        }

        return response;
    }


}
