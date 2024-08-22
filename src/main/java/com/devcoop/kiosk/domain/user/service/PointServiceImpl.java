package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements UserPointService {

    private final UserRepository userRepository;

    @Transactional
    public Object deductPoints(UserPointRequest userPointRequestDto) {
        System.out.println("deductPoint 실행");
        String userCode = userPointRequestDto.userCode(); // codeNumber를 userCode로 변경
        User user = userRepository.findByUserCode(userCode); // 메서드 호출 수정

        System.out.println(user);

        try {
            if (user != null && user.getUserPoint() >= userPointRequestDto.totalPrice()) {
                System.out.println("if 문 통과");
                int newPoint = user.getUserPoint() - userPointRequestDto.totalPrice();
                System.out.println(newPoint);
                user.setUserPoint(newPoint); // setPoint를 setUserPoint로 변경
                userRepository.save(user);

                System.out.println("결제 후 남은 포인트는 : " + newPoint + "원 입니다.");
                return newPoint;
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
