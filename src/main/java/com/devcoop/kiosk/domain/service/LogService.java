package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.presentation.dto.PayLogRequestDto;

public interface LogService {
    void savePayLog(PayLogRequestDto payLogRequestDto);
}
