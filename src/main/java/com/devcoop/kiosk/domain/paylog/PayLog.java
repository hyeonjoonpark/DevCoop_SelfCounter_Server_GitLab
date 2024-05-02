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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payNum;
    private String codeNumber;
    @CreationTimestamp
    private LocalDateTime date = LocalDateTime.now();
    private Short type;
    private int innerPoint;
    private int point;
    private String chargerId;
    private String verifyKey;
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
