package com.devcoop.kiosk.domain.paylog;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "pay_log")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayLog {
    @Id
    @Column(name = "pay_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payNum;
    @Column(name = "code_number")
    private String codeNumber;
    @Column(name = "date")
    @CreationTimestamp
    private LocalDateTime date = LocalDateTime.now();
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

    @Builder
    public PayLog(int payNum, String codeNumber, LocalDateTime date, Short type, int innerPoint, int point, String chargerId, String verifyKey, String studentName) {
        this.payNum = payNum;
        this.codeNumber = codeNumber;
        this.date = date;
        this.type = type;
        this.innerPoint = innerPoint;
        this.point = point;
        this.chargerId = chargerId;
        this.verifyKey = verifyKey;
        this.studentName = studentName;
    }
}
