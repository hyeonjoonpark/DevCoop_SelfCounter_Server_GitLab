package com.devcoop.kiosk.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "pay_log")
@Table(name = "pay_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayLogEntity {
    @Id
    @Column(name = "pay_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int payNum;
    @Column(name = "code_number")
    private String codeNumber;
    @Column(name = "date")
    private Date date;
    @Column(name = "type")
    private Short type;
    @Column(name = "inner_point")
    private int innerPoint;
    @Column(name = "point")
    private int point;
    @Column(name = "charger")
    private String charger;
    @Column(name = "verify_key")
    private String verifyKey;
    @Column(name = "student_name")
    private String studentName;
}
