package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final PayLogRepository payLogRepository;

    private final UserRepository userRepository;


    @Override
    public ResponseEntity<Object> savePayLog(PayLogRequestDto payLogRequestDto) {
        try {
            PayLog payLog = payLogRequestDto.toEntity();
             
            payLogRepository.save(payLog);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
