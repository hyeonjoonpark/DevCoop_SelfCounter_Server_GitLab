package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.repository.UserRepository;
import com.devcoop.kiosk.global.exception.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class UserPointController {
    private final UserRepository userRepository;

    public UserPointController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping("/pay")
    public Object deductPoints(@RequestBody RequestDto requestDto) {
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
     public static class RequestDto {
        private String codeNumber;
        private int totalPrice;
    }
}
