package com.devcoop.kiosk.domain.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;

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
