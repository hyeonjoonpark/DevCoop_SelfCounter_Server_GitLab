package com.devcoop.kiosk.domain.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Claims;


@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;
    private String secretKey = "KiOSkSEcrET";

    private Key getSignInKey(String secretKey) {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String createJwt(String codeNumber) {

        return Jwts.builder()
                .signWith(getSignInKey(secretKey))
                .setSubject(codeNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(this.ACCESS_TOKEN_EXPIRE_TIME))
                .compact();
    }
    public String extractCodeNumberFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            // 토큰 파싱 또는 검증 중에 예외가 발생한 경우
            return null; // 추출 실패 시 null 반환 또는 예외 처리 방식에 따라 수정
        }
    }
}
