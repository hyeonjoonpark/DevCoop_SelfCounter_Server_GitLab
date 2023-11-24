package com.devcoop.kiosk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll() // 특정 URL은 인증 없이 접근 허용
                .anyRequest().authenticated() // 그 외 모든 요청은 인증이 필요
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll() // 로그인 페이지는 인증 없이 접근 허용
                .and()
                .logout()
                .permitAll(); // 로그아웃은 인증 없이 접근 허용
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**"); // 정적 리소스는 Spring Security 필터를 적용하지 않음
    }

    // PasswordEncoder 빈 등록 (비밀번호 인코딩에 사용)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
