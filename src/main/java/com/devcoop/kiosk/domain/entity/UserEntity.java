package com.devcoop.kiosk.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
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
}
