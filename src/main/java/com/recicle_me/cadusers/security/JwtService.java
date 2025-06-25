package com.recicle_me.cadusers.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            Arrays.copyOf("superSecretKey".getBytes(StandardCharsets.UTF_8), 32)
    );

    public String generateToken(int userId) {
        return Jwts.builder()
                .setSubject(Integer.toString(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
