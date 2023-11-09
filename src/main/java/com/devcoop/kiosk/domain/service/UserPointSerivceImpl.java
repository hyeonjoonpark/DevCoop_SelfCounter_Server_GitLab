package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.repository.UserRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPointSerivceImpl implements UserPointService {

    @Autowired UserRepository userRepository;

    public Object deductPoints(UserPointRequestDto requestDto) {
        UserEntity user = userRepository.findByCodeNumber(requestDto.getCodeNumber());

        try {
            if (user != null && user.getPoint() >= requestDto.getTotalPrice()) {
                int newPoint = user.getPoint() - requestDto.getTotalPrice();
                user.setPoint(newPoint);
                userRepository.save(user);
                return user.getPoint();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }

        return null;
    }
}
