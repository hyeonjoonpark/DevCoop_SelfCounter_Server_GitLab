package com.devcoop.kiosk.global.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
  // isExpired 메서드 구현
  public static boolean isExpired(String token, String secretKey) {
    byte[] decodedKey = Base64.getDecoder().decode(secretKey);
    SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);
    return Jwts.parserBuilder()
      .setSigningKey(decodedKey)
      .build()
      .parseClaimsJws(token)
      .getBody()
      .getExpiration()
      .before(new Date());
  }

  public static String getCodeNumber(String token, String secretKey) {
    // base64 인코딩된 secretKey 문자열을 디코딩합니다.
    byte[] decodedKey = Base64.getDecoder().decode(secretKey);
    // 디코딩된 secretKey 바이트 배열로부터 SecretKey 인스턴스를 생성합니다.
    SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);

    // secretKey를 SecretKey 인스턴스로 사용하여 새 API를 사용합니다.
    return Jwts.parserBuilder()
      .setSigningKey(originalKey)
      .build() // 파서 설정을 완료합니다.
      .parseClaimsJws(token)
      .getBody()
      .get("codeNumber", String.class);
  }

  public static String createJwt(String codeNumber, String secretKey, Long exprTime) {
    Claims claims = Jwts.claims();
    claims.put("codeNumber", codeNumber);

    byte[] decodedKey = Base64.getDecoder().decode(secretKey);
    SecretKey key = Keys.hmacShaKeyFor(decodedKey);

    return Jwts.builder()
      .setClaims(claims)
      .signWith(key)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + exprTime))
      .compact();
  }
}
