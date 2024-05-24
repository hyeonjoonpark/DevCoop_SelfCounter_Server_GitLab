package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskItemInfo;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.receipt.types.ReceiptType;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SelfCounterService {

  private final PayLogRepository payLogRepository;
  private final UserRepository userRepository;
  private final KioskReceiptRepository kioskReceiptRepository;
  private final ItemRepository itemRepository;

  public ResponseEntity<Object> deductPoints(UserPointRequest userPointRequest) {
    System.out.println("userPointRequestDto = " + userPointRequest);
    String codeNumber = userPointRequest.codeNumber();
    User user = userRepository.findByCodeNumber(codeNumber);

    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
    }

    if (user.getPoint() >= userPointRequest.totalPrice()) {
      int newPoint = user.getPoint() - userPointRequest.totalPrice();
      user.setPoint(newPoint);
      userRepository.save(user);

      return ResponseEntity.ok(newPoint); // 새로운 포인트 반환
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제하는 것에 실패했습니다.");
    }
  }

  public ResponseEntity<Object> savePayLog(PayLogRequest payLogRequest) {
    try {
      User user = userRepository.findByStudentName(payLogRequest.studentName());
      PayLog payLog = payLogRequest.toEntity(user.getPoint());
      payLogRepository.save(payLog);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 로그 저장 중 오류가 발생하였습니다.");
    }
  }

  public ResponseEntity<String> saveReceipt(KioskRequest kioskRequest) {
    try {
      List<KioskItemInfo> requestItems = kioskRequest.getItems();
      System.out.println("requestItemList = " + requestItems);
      for (KioskItemInfo itemInfo : requestItems) {
        Item item = itemRepository.findByItemName(itemInfo.itemName());

        if (item == null) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("없는 상품입니다.");
        }

        KioskReceipt kioskReceipt = KioskReceipt.builder()
          .dcmSaleAmt(itemInfo.dcmSaleAmt())
          .itemName(item.getItemName())
          .saleQty((short) itemInfo.saleQty())
          .userId(kioskRequest.getUserId())
          .itemId(String.valueOf(item.getItemId()))
          .saleYn(ReceiptType.Y)
          .build();

        kioskReceiptRepository.save(kioskReceipt);
      }
      return ResponseEntity.ok("영수증이 성공적으로 저장되었습니다.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영수증 저장 중 오류가 발생하였습니다.");
    }
  }
}
