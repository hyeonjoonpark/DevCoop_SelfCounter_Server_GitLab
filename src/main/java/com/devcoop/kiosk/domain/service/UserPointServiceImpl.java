package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.repository.UserRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserPointServiceImpl implements UserPointService {

    @Autowired UserRepository userRepository;
    @Autowired ReceiptRepository receiptRepository;

    public Object deductPoints(UserPointRequestDto userPointRequestDto) {
        System.out.println("deductPoint 실행");
        UserEntity user = userRepository.findByCodeNumber(userPointRequestDto.getCodeNumber());

        System.out.println(user);

        try {
            if (user != null && user.getPoint() >= userPointRequestDto.getTotalPrice()) {
                System.out.println("if 문 통과");
                int newPoint = user.getPoint() - userPointRequestDto.getTotalPrice();
                System.out.println(newPoint);
                user.setPoint(newPoint);
                userRepository.save(user);

                System.out.println("결제 후 남은 포인트는 : " + newPoint + "원 입니다.");
                return newPoint;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }

        return null;
    }
}
