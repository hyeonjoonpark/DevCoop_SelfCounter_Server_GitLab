package com.devcoop.kiosk.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private String studentName;
    private int point;

    public UserResponseDto() {

    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
