package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.paylog.PayLog;
import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.paylog.repository.PayLogRepository;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class LogServiceImpl implements LogService {

    private final PayLogRepository payLogRepository;

    private final UserRepository userRepository;

    private final PayLog payLog;

    @Autowired
    public LogServiceImpl(PayLogRepository payLogRepository, UserRepository userRepository, PayLog payLog) {
        this.payLogRepository = payLogRepository;
        this.userRepository = userRepository;
        this.payLog = payLog;
    }

    @Override
    public ResponseEntity<Object> savePayLog(PayLogRequestDto payLogRequestDto) {
        try {
            payLog.setCodeNumber(payLogRequestDto.getCodeNumber());
            payLog.setDate(ZonedDateTime.now());
            payLog.setInnerPoint(payLogRequestDto.getInnerPoint());

            User user = userRepository.findPointByCodeNumber(payLogRequestDto.getCodeNumber());
            payLog.setPoint(user.getPoint() - payLogRequestDto.getInnerPoint());

            payLog.setChargerId(payLogRequestDto.getChargerId());
            payLog.setVerifyKey("test");
            payLog.setStudentName(payLogRequestDto.getStudentName());
             
            payLogRepository.save(payLog);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
