package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.item.repository.ItemRepository;
import com.devcoop.kiosk.domain.receipt.repository.KioskReceiptRepository;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import com.devcoop.kiosk.global.exception.GlobalException;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
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
public class SelfCounterService {

  private final PayLogRepository payLogRepository;
  private final UserRepository userRepository;
  private final KioskReceiptRepository kioskReceiptRepository;
  private final ItemRepository itemRepository;

  @Transactional
  public Object deductPoints(UserPointRequest userPointRequest) {
    System.out.println("userPointRequestDto = " + userPointRequest);
    String codeNumber = userPointRequest.codeNumber();
    User user = userRepository.findByCodeNumber(codeNumber);
    log.info("user = {}", user);

    try {
      if(user == null) {
        throw new GlobalException(ErrorCode.USER_NOT_FOUND);
      }

      if (user.getPoint() >= userPointRequest.totalPrice()) {
        int newPoint = user.getPoint() - userPointRequest.totalPrice();
        log.info("newPoint = {}", newPoint);
        user.setPoint(newPoint);
        userRepository.save(user);

        return newPoint;
      }

      return new RuntimeException("결제하는 것에 실패했습니다");

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value());
    } catch (GlobalException e) {
        throw new RuntimeException(e);
    }
  }

  @Transactional
  public ResponseEntity<Object> savePayLog(PayLogRequest payLogRequest) {
    PayLog payLog = payLogRequest.toEntity();
    payLogRepository.save(payLog);
    return ResponseEntity.ok().build();
  }

  @Transactional
  public ResponseEntity<Object> saveReceipt(KioskRequest kioskRequest) {

    List<Item> items = itemRepository.findItemEntitiesByItemName(kioskRequest.itemName());
    System.out.println("items = " + items);
    if (!items.isEmpty()) {
      Item item = items.get(0);
      String itemId = String.valueOf(item.getItemId());
      KioskReceipt kiosk = kioskRequest.toEntity(itemId);
      kioskReceiptRepository.save(kiosk);
    }

    return ResponseEntity.ok().build();
  }
}
