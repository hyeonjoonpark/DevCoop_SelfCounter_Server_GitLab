package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.repository.UserRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class UserPointServiceImpl implements UserPointService {

    private final UserRepository userRepository;

    @Autowired
    public UserPointServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
