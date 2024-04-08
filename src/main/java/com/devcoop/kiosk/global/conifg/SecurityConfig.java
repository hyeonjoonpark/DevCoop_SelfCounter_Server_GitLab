package com.devcoop.kiosk.global.conifg;

import com.devcoop.kiosk.domain.item.service.ItemSelectService;
import com.devcoop.kiosk.domain.paylog.service.LogService;
import com.devcoop.kiosk.domain.paylog.service.SelfCounterService;
import com.devcoop.kiosk.domain.receipt.service.ReceiptService;
import com.devcoop.kiosk.global.utils.security.JwtFilter;
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
  private final SelfCounterService selfCounterService;
  private final ItemSelectService itemSelectService;
  private final LogService logService;
  private final ReceiptService receiptService;

  @Value("${jwt.secret}")
  private String secretKey;

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
          .requestMatchers(HttpMethod.POST, "/kiosk/auth/signIn").permitAll()
          .requestMatchers("/kiosk/auth/pwChange").permitAll()
          .requestMatchers("/itemRecommend").permitAll()
          .requestMatchers("/kiosk/save/paylog").permitAll()
          .requestMatchers("/kiosk/save/receipt").permitAll()
          .requestMatchers("/kiosk/executePayments").permitAll()
          .requestMatchers("/itemSelect").permitAll()
          .requestMatchers("/kiosk/pay").permitAll()
          .anyRequest().permitAll()
      )
      .sessionManagement(
        session -> session
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .addFilterBefore(new JwtFilter(secretKey, selfCounterService, logService, receiptService, itemSelectService), UsernamePasswordAuthenticationFilter.class);


    return http.build();
  }
}
