package com.devcoop.kiosk.global.conifg;

import com.devcoop.kiosk.global.utils.security.JwtFilter;
import com.devcoop.kiosk.global.utils.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Value("${jwt.secret}")
  private String secretKey;

  @Bean
  public JwtFilter jwtFilter() {
    return new JwtFilter(secretKey);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(Customizer.withDefaults())
      .csrf(AbstractHttpConfigurer::disable)
      .formLogin(AbstractHttpConfigurer::disable)
      .httpBasic(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        request -> request
          .requestMatchers(HttpMethod.POST, "/kiosk/auth/signIn").permitAll() // 로그인 API
          .requestMatchers(HttpMethod.GET, "/kiosk/item/ai/suggest").permitAll() // 상품추천 API
          .requestMatchers(HttpMethod.GET, "/kiosk/event-item/get-item").permitAll() // 행사상품 조회 API
          .requestMatchers(HttpMethod.GET, "/kiosk/item/top/list").permitAll() // 인기상품 조회 API
          .requestMatchers(HttpMethod.PUT, "/kiosk/auth/pwChange").permitAll() // 비밀번호 변경 API
          .requestMatchers(HttpMethod.POST, "/kiosk/executePayments").authenticated() // 결제, 결제정보 저장, 영수증 저장 모두 하나의 트랜잭션 안에서 실행되는 API
          .requestMatchers(HttpMethod.GET, "/itemSelect").authenticated() // 상품정보 불러오기 API
          .requestMatchers(HttpMethod.PUT, "/kiosk/pay").authenticated() // 결제 API
          .anyRequest().permitAll()
      )
      .sessionManagement(
        session -> session
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);


    return http.build();
  }
}
