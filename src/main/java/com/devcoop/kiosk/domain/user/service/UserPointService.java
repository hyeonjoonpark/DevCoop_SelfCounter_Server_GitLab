package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequest;

public interface UserPointService {
    Object deductPoints(UserPointRequest requestDto);
}
