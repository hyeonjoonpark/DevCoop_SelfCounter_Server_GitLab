package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;
import com.devcoop.kiosk.domain.user.service.UserPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "pay", description = "Pay API")
public class PointController {

    private final UserPointService userPointService;
    @PutMapping("/pay")
    @Operation(summary = "pay", description = "결제")
    public Object deductPoints(@RequestBody UserPointRequest requestDto) {
        Object deductedUserPoint = userPointService.deductPoints(requestDto);
        return deductedUserPoint;
    }
}
