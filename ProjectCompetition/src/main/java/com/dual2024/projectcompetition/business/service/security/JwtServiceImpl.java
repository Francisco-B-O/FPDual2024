package com.dual2024.projectcompetition.business.service.security;

import com.dual2024.projectcompetition.business.businessobject.UserBO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Implementation of the JwtService interface.
 *
 * @author Francisco Balonero Olivera
 * @see JwtService
 */
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public String generateToken(UserBO user, Map<String, Object> extraClaims) {
        log.info("Generating JWT token for user with email: {}", user.getEmail());
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (60 * EXPIRATION_MINUTES * 1000));
        String jwtToken = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
        log.info("JWT token generated successfully.");
        return jwtToken;
    }

    private Key generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    @Override
    public String extractEmail(String jwt) {
        String email = extractAllClaims(jwt).getSubject();
        log.info("Extracted email {} from JWT token.", email);
        return email;
    }

    @Override
    public Claims extractAllClaims(String jwt) {
        log.info("Extracting claims from JWT token.");
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

}
