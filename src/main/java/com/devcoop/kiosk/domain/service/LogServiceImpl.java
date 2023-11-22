package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.PayLogEntity;
import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.repository.PayLogRepository;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Service
public class LogServiceImpl implements LogService {

    private final PayLogRepository payLogRepository;

    private final UserRepository userRepository;

    private final PayLogEntity payLogEntity;

    @Autowired
    public LogServiceImpl(PayLogRepository payLogRepository, UserRepository userRepository, PayLogEntity payLogEntity) {
        this.payLogRepository = payLogRepository;
        this.userRepository = userRepository;
        this.payLogEntity = payLogEntity;
    }

    @Override
    public ResponseEntity<Object> savePayLog(PayLogRequestDto payLogRequestDto) {
        try {
            payLogEntity.setCodeNumber(payLogRequestDto.getCodeNumber());
            payLogEntity.setDate(ZonedDateTime.now());
            payLogEntity.setInnerPoint(payLogRequestDto.getInnerPoint());

            UserEntity user = userRepository.findPointByCodeNumber(payLogRequestDto.getCodeNumber());
            payLogEntity.setPoint(user.getPoint() - payLogRequestDto.getInnerPoint());

            payLogEntity.setChargerId(payLogRequestDto.getChargerId());
            payLogEntity.setVerifyKey("test");
            payLogEntity.setStudentName(payLogRequestDto.getStudentName());

            payLogRepository.save(payLogEntity);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
