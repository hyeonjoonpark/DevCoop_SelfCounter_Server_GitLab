package com.devcoop.kiosk.global.utils.security;

import com.devcoop.kiosk.domain.item.service.ItemSelectService;
import com.devcoop.kiosk.domain.paylog.service.LogService;
import com.devcoop.kiosk.domain.paylog.service.SelfCounterService;
import com.devcoop.kiosk.domain.receipt.service.ReceiptService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
  private final String secretKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // header에서 authorization을 꺼냄
    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    log.info("Authorization: {}", authorization);

    // token을 보내지 않으면 block
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      log.error("authorization을 잘못 보냈습니다");
      filterChain.doFilter(request, response);
      return;
    }

    // token 꺼내기
    String token = authorization.split(" ")[1];

    // Token이 만료되었는지 확인
    if (JwtUtil.isExpired(token, secretKey)) {
      log.error("토큰이 만료되었습니다");
      filterChain.doFilter(request, response);
      return;
    }

    // UserId 토큰에서 꺼내기
    String codeNumber = JwtUtil.getCodeNumber(token, secretKey);
    log.info("codeNumber: {}", codeNumber);

    // 권한부여
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(codeNumber, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));

    // Detail을 넣어줍니다
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    filterChain.doFilter(request, response);
  }
}
