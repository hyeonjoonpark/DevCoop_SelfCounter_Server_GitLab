package com.devcoop.kiosk.domain.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;
    private final long REFRESH_TOKEN_EXPIRE_TIME = ACCESS_TOKEN_EXPIRE_TIME * 12 * 7;

    @AllArgsConstructor
    private enum TokenType {
        ACCESS_TOKEN("accessToken");
        String value;
    }

    @AllArgsConstructor
    private enum TokenClaimName {
        USER_ID("id"),
        TOKEN_TYPE("tokenType");
        String value;
    }

    private Key getSignInKey(String secretKey) {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String createJwt(String codeNumber) {
        String secretKey = "KiOSkSEcrET";
        return Jwts.builder()
                .signWith(getSignInKey(secretKey))
                .setSubject(codeNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(this.ACCESS_TOKEN_EXPIRE_TIME))
                .compact();
    }
}
