package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.presentation.dto.UserPointRequestDto;

public interface UserPointService {
    Object deductPoints(UserPointRequestDto requestDto);
}
