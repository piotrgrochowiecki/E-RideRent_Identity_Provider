package com.piotrgrochowiecki.eriderent_identity_provider.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION_MILLISECONDS = 60 * 60 * 1000; //1 hour
    @Value("${app.jwt.secret}")
    private String SECRET_KEY_STRING;
    private Key SECRET_KEY;

    @PostConstruct
    public void init() {
        SECRET_KEY = new SecretKeySpec(SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getUuid() + "," + user.getEmail())
                .issuer("Piotr Grochowiecki")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION_MILLISECONDS))
                .signWith(SECRET_KEY)
                .compact();
    }

}
