package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;

public interface UserPointService {
    Object deductPoints(UserPointRequestDto requestDto);
}
