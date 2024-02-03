package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.user.service.UserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PointController {

    private final UserPointService userPointService;
    @PutMapping("/pay")
    public Object deductPoints(@RequestBody UserPointRequestDto requestDto) {
        Object deductedUserPoint = userPointService.deductPoints(requestDto);
        return deductedUserPoint;
    }
}
