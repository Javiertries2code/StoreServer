package com.elorrieta.storeapi.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    @Value("${jwt.expiration}")
//    private long jwtExpirationMs;
//
//    @Value("${jwt.refresh-token.expiration}")
//    private long jwtRefreshExpirationMs;
//
//    private SecretKey key;
//
//    @PostConstruct
//    public void init() {
//        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//    }
//
//    // Generate JWT token
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .subject(username)
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // Generate Refresh Token
//    public String generateRefreshToken(String username) {
//        return Jwts.builder()
//                .subject(username)
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // Extract username from token
//    public String getUsernameFromToken(String token) {
//        return Jwts.parser()
//                .verifyWith(key)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                .getSubject();
//    }
//
//    // Validate token
//    public boolean validateJwtToken(String token) {
//        try {
//            Jwts.parser()
//                .verifyWith(key)
//                .build()
//                .parseSignedClaims(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            System.out.println("Invalid or expired JWT token: " + e.getMessage());
//        }
//        return false;
//    }
}