package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.item.Item;
import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.paylog.presentation.dto.KioskRequest;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequest;
import com.devcoop.kiosk.domain.receipt.KioskReceipt;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
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

import java.util.ArrayList;
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
  public ResponseEntity<String> saveReceipt(KioskRequest kioskRequest) {
    // KioskRequest로부터 KioskReceipt 리스트를 생성
    List<KioskReceipt> kioskReceipts = kioskRequest.toEntity();
    List<KioskReceipt> receiptsToSave = new ArrayList<>();

    for (KioskReceipt kioskReceipt : kioskReceipts) {
      log.info("kioskReceipt = {}", kioskReceipt);
      // 아이템 이름으로 Item 객체들을 조회
      List<Item> items = itemRepository.findItemEntitiesByItemName(kioskReceipt.getItemName());
      log.info("items = {}", items);
      if (!items.isEmpty()) {
        for (Item item : items) {
          log.info("item = {}", item);
          // 각 Item에 대해 새로운 KioskReceipt 객체를 생성 (또는 복제)하고, itemId 설정
          KioskReceipt newReceipt = new KioskReceipt();
          log.info("newReceipt = {}", newReceipt);
          newReceipt.setItemId(String.valueOf(item.getItemId()));

          // 생성된 KioskReceipt를 저장할 리스트에 추가
          receiptsToSave.add(newReceipt);
          log.info("receiptsToSave = {}", receiptsToSave);
        }
      }
    }

    // 모든 KioskReceipt 객체를 한 번에 저장
    kioskReceiptRepository.saveAll(receiptsToSave);

    return ResponseEntity.status(HttpStatus.OK).body("성공적으로 저장하였습니다");
  }


}
