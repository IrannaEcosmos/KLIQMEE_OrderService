package com.example.Test.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class JWTUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private SecretKey getSigningKey() throws NoSuchAlgorithmException {
        byte[] secretBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedSecret = md.digest(secretBytes);
        return Keys.hmacShaKeyFor(hashedSecret);
    }

    public String extractUserId(String token) {
        try {
            SecretKey key = getSigningKey();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            
            return claims.get("userId", String.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }
}