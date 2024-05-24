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
import com.devcoop.kiosk.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

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

  // ResponseEntity<Object> 대신 int 또는 다른 적절한 타입을 반환하도록 변경
  public int deductPoints(UserPointRequest userPointRequest) throws RuntimeException {
    System.out.println("userPointRequestDto = " + userPointRequest);
    String codeNumber = userPointRequest.codeNumber();
    User user = userRepository.findByCodeNumber(codeNumber);

    if(user == null) {
      throw new NotFoundException("사용자를 찾을 수 없습니다."); // 예외 던지기
    }

    if (user.getPoint() >= userPointRequest.totalPrice()) {
      int newPoint = user.getPoint() - userPointRequest.totalPrice();
      user.setPoint(newPoint);
      userRepository.save(user);

      return newPoint; // 새로운 포인트 반환
    } else {
      throw new RuntimeException("결제하는 것에 실패했습니다"); // 예외 던지기
    }
  }


  public void savePayLog(PayLogRequest payLogRequest) throws RuntimeException {
    User user = userRepository.findByStudentName(payLogRequest.studentName());
    PayLog payLog = payLogRequest.toEntity(user.getPoint());
    payLogRepository.save(payLog);
  }

  public void saveReceipt(KioskRequest kioskRequest) throws GlobalException {
    List<KioskItemInfo> requestItems = kioskRequest.getItems();
    System.out.println("requestItemList = " + requestItems);
    for (KioskItemInfo itemInfo : requestItems) {
      Item item = itemRepository.findByItemName(itemInfo.itemName());

      if (item == null) {
        throw new NotFoundException("없는 상품입니다");
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
  }
}
