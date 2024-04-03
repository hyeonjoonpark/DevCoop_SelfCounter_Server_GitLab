package com.devcoop.kiosk.domain.user.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
  // isExpired 메서드 구현
  public static boolean isExpired(String token, String secretKey) {
    return Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(token)
      .getBody()
      .getExpiration()
      .before(new Date());
  }

  public static String getCodeNumber(String token, String secretKey) {
    return Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(token)
      .getBody()
      .get("codeNumber", String.class);
  }

  public static String createJwt(String codeNumber, String secretKey, Long exprTime) {
    Claims claims = Jwts.claims();

    claims.put("codeNumber", codeNumber);

    return Jwts.builder()
      .setClaims(claims)
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + exprTime))
      .compact();
  }
}
