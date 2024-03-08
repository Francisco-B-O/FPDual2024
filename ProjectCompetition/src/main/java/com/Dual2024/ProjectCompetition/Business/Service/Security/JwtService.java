package com.Dual2024.ProjectCompetition.Business.Service.Security;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import io.jsonwebtoken.Claims;

import java.util.Map;

/**
 * Interface that contains the methods of the service referring to jwt.
 *
 * @author Francisco Balonero Olivera
 */
public interface JwtService {
    /**
     * Method that generate jwt.
     *
     * @param user            The user
     * @param stringObjectMap The extra claims
     * @return the string
     */
    public String generateToken(UserBO user, Map<String, Object> stringObjectMap);

    /**
     * method that extracts the email from a jwt.
     *
     * @param jwt The jwt
     * @return The email
     */
    public String extractEmail(String jwt);

    /**
     * Method that extract all claims.
     *
     * @param jwt The jwt
     * @return The claims
     */
    public Claims extractAllClaims(String jwt);
}
