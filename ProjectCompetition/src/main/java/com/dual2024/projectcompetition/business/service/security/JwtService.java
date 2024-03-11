package com.dual2024.projectcompetition.business.service.security;

import com.dual2024.projectcompetition.business.businessobject.UserBO;
import io.jsonwebtoken.Claims;

import java.util.Map;

/**
 * Interface that contains the methods of the service referring to JSON Web Tokens (JWT).
 *
 * <p>This interface defines methods for generating and extracting information from JWTs.
 * Implementing classes should provide concrete implementations of these methods to handle JWT-related logic.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Instantiate or inject JwtService
 * JwtService jwtService = // instantiate or inject the implementation
 *
 * // Generate a JWT for a user with extra claims
 * UserBO user = // obtain the user information
 * Map<String, Object> extraClaims = // create a map of extra claims
 * String jwt = jwtService.generateToken(user, extraClaims);
 *
 * // Extract the email from a JWT
 * String extractedEmail = jwtService.extractEmail(jwt);
 *
 * // Extract all claims from a JWT
 * Claims allClaims = jwtService.extractAllClaims(jwt);
 * }
 * </pre>
 *
 * <p>Implementing classes should handle exceptions appropriately to manage errors related to JWT operations.</p>
 *
 * <p>Instances of this interface are typically used in the security layer of the application to handle JWT-related functionality.</p>
 *
 * @author Francisco Balonero Olivera
 * @see io.jsonwebtoken
 */
public interface JwtService {
    /**
     * Method that generates a JSON Web Token (JWT) for a user with extra claims.
     *
     * @param user        The user for whom the JWT is generated
     * @param extraClaims Additional claims to include in the JWT
     * @return The generated JWT as a string
     */
    String generateToken(UserBO user, Map<String, Object> extraClaims);

    /**
     * Method that extracts the email from a JSON Web Token (JWT).
     *
     * @param jwt The JWT from which to extract the email
     * @return The email extracted from the JWT
     */
    String extractEmail(String jwt);

    /**
     * Method that extracts all claims from a JSON Web Token (JWT).
     *
     * @param jwt The JWT from which to extract all claims
     * @return All claims extracted from the JWT as a Claims object
     */
    Claims extractAllClaims(String jwt);
}
