package com.Dual2024.ProjectCompetition.Business.Service.Security;

import java.util.Map;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;

import io.jsonwebtoken.Claims;

public interface JwtService {
	public String generateToken(UserBO user, Map<String, Object> stringObjectMap);

	public String extractEmail(String jwt);

	public Claims extractAllClaims(String jwt);
}
