package com.devcoop.kiosk.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String studentName;
    private int point;

    public UserResponse() {

    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
