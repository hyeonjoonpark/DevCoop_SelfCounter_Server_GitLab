package com.devcoop.kiosk.domain.service;
import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.PaymentRequestDto;
import com.devcoop.kiosk.domain.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.provider.TokenProvider;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final ReceiptService receiptService;
    private final LogService logService;
    private final TokenProvider tokenProvider;

    private final UserRepository userRepository;

    @Autowired
    public PaymentsServiceImpl(
            ReceiptService receiptService,
            LogService logService,
            TokenProvider tokenProvider,
            UserRepository userRepository) {
        this.receiptService = receiptService;
        this.logService = logService;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }


//    public Object deductPoints(String codeNumber) {
//        System.out.println("deductPoint 실행");
//        UserEntity user = userRepository.findByCodeNumber(codeNumber);
//        System.out.println(user);
//
//        try {
//            if (user != null && user.getPoint() >= userPointRequestDto.getTotalPrice()) {
//                System.out.println("if 문 통과");
//                int newPoint = user.getPoint() - userPointRequestDto.getTotalPrice();
//                System.out.println(newPoint);
//                user.setPoint(newPoint);
//                userRepository.save(user);
//
//                System.out.println("결제 후 남은 포인트는 : " + newPoint + "원 입니다.");
//                return newPoint;
//            }
//
//            return ResponseEntity.ok().build();
//        } catch(Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

    @Override
    public ResponseEntity<Object> processPaymentAndSaveReceipts(PaymentRequestDto requestDto, String codeNumber) {
        // 여기에서 userBarcode와 items를 사용하여 결제 및 영수증 저장 로직을 구현합니다.
        // receiptService와 logService를 사용하여 필요한 처리를 수행합니다.

        // 1. 총액 계산
//        int usePoint = requestDto



        System.out.println("1. deduct point");
//        deductPoints();
        System.out.println(requestDto);
       // String codeNumber = tokenProvider.extractCodeNumberFromToken(requestDto.getToken());
       // System.out.println(codeNumber);
//        // 예시: 영수증 저장
//        receiptService.saveReceipt(requestDto, codeNumber);
//
//        // 예시: 결제 정보 로그 저장
//        logService.savePayLog(requestDto, codeNumber);

        // 처리 결과를 반환
        return ResponseEntity.ok().build();
    }
}