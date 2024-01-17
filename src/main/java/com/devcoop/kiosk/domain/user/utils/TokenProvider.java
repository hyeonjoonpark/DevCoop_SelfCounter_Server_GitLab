package com.devcoop.kiosk.domain.user.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {
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
