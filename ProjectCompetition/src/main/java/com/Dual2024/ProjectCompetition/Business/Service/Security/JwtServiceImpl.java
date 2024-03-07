package com.Dual2024.ProjectCompetition.Business.Service.Security;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	@Value("${security.jwt.expiration-minutes}")
	private long EXPIRATION_MINUTES;
	@Value("${security.jwt.secret-key}")
	private String SECRET_KEY;

	@Override
	public String generateToken(UserBO user, Map<String, Object> extraClaims) {
		Date issuedAt = new Date(System.currentTimeMillis());
		Date expiration = new Date(issuedAt.getTime() + (60 * EXPIRATION_MINUTES * 1000));
		return Jwts.builder().setClaims(extraClaims).setSubject(user.getEmail()).setIssuedAt(issuedAt)
				.setExpiration(expiration).setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.signWith(generateKey(), SignatureAlgorithm.HS256).compact();

	}

	private Key generateKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	@Override
	public String extractEmail(String jwt) {
		return extractAllClaims(jwt).getSubject();
	}

	@Override
	public Claims extractAllClaims(String jwt) {
		return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();

	}

}
