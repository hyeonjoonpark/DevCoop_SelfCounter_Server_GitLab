package com.devcoop.kiosk.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(name = "student_number")
    private int studentNumber;
    @Column(name = "code_number")
    private String codeNumber;
    @Column(name = "pin")
    private String pin;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "point")
    private int point;
    @Column(name = "is_admin")
    private Short isAdmin;
    @Column(name = "is_coop")
    private Short isCoop;
    @Column(name = "type")
    private String type;
    @Column(name = "point_status")
    private String pointStatus;
    @Column(name = "ref_token")
    private String refToken;

    @Builder
    public User(
            int studentNumber, String codeNumber,
            String pin, String studentName,
            String email, String password,
            int point, Short isAdmin,
            Short isCoop, String type,
            String pointStatus, String refToken
    ) {
        this.studentNumber = studentNumber;
        this.codeNumber = codeNumber;
        this.pin = pin;
        this.studentName = studentName;
        this.email = email;
        this.password = password;
        this.point = point;
        this.isAdmin = isAdmin;
        this.isCoop = isCoop;
        this.type = type;
        this.pointStatus = pointStatus;
        this.refToken = refToken;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
