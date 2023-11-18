package com.devcoop.kiosk.domain.presentation;

import com.devcoop.kiosk.domain.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiosk")
public class LogSaveController {

    @Autowired LogService logService;

    @PostMapping("/save/paylog")
    public void savePayLog(@RequestBody PayLogRequestDto payLogRequestDto) {
        logService.savePayLog(payLogRequestDto);
    }
}
