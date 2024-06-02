package com.devcoop.kiosk.domain.user;

import com.devcoop.kiosk.domain.user.types.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  private int studentNumber;
  private String codeNumber;
  private String pin;
  private String studentName;
  private String email;
  private String password;
  private int point;
  private Short isAdmin;
  private Short isCoop;
  private String type;
  private String pointStatus;
  private String refToken;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role = Role.ROLE_USER_NOT_COOP; // default 값은 ROLE_USER

  @Builder
  public User(
    int studentNumber, String codeNumber, String pin, String studentName,
    String email, String password, int point, Short isAdmin,
    Short isCoop, String type, String pointStatus, String refToken, Role role
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
    this.role = role;
  }

  public void setPoint(int point) {
    this.point = point;
  }

  public void update(String pin) {
    this.pin = pin;
  }
}
