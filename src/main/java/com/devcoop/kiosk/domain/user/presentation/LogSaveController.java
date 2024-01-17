package com.devcoop.kiosk.domain.user.presentation;

import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.paylog.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
@RequiredArgsConstructor
public class LogSaveController {

    private final LogService logService;

    @PostMapping("/save/paylog")
    public void savePayLog(@RequestBody PayLogRequestDto payLogRequestDto) {
        logService.savePayLog(payLogRequestDto);
    }
}