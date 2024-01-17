package com.devcoop.kiosk.domain.user.details;

import com.devcoop.kiosk.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 빈 권한 목록을 반환
    }

    public String getPassword() {
        return user.getPin();
    }

    public int getPoint(){
        return user.getPoint();
    }
    public String getUsername() {
        return user.getStudentName();
    }


    public boolean isAccountNonExpired() {
        // 계정 만료 여부를 반환하는 로직을 나중에 추가
        return false; // 나중에 true 또는 false로 수정
    }


    public boolean isAccountNonLocked() {
        return this.user.getIsCoop() == 1;
    }


    public boolean isCredentialsNonExpired() {
        // 자격 증명 만료 여부를 반환하는 로직을 나중에 추가
        return false; // 나중에 true 또는 false로 수정
    }


    public boolean isEnabled() {
        // 계정 활성화 여부를 반환하는 로직을 나중에 추가
        return this.user.getIsCoop() == 1; // 나중에 true 또는 false로 수정
    }
}