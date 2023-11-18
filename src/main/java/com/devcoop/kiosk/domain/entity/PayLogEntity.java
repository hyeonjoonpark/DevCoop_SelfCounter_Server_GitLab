package com.devcoop.kiosk.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity(name = "pay_log")
@Table(name = "pay_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PayLogEntity {
    @Id
    @Column(name = "pay_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payNum;
    @Column(name = "code_number")
    private String codeNumber;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "type")
    private Short type;
    @Column(name = "inner_point")
    private int innerPoint;
    @Column(name = "point")
    private int point;
    @Column(name = "charger_id")
    private String chargerId;
    @Column(name = "verify_key")
    private String verifyKey;
    @Column(name = "student_name")
    private String studentName;
}
