package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.Role;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenService {

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
                .subject(user.uuid() + "," + user.email())
                .issuer("Piotr Grochowiecki")
                .claim("role", user.role()
                        .toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION_MILLISECONDS))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Role extractRole(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Role.valueOf(claims.get("role", String.class));
    }

}
