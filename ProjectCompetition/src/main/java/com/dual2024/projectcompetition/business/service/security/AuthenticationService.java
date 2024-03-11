package com.dual2024.projectcompetition.business.service.security;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.presentation.dto.AuthenticationRequest;
import com.dual2024.projectcompetition.presentation.dto.AuthenticationResponse;

/**
 * Interface that contains the methods of the authentication service.
 *
 * <p>This interface defines methods for user authentication, including login and retrieving the ID of the authenticated user.
 * Implementing classes should provide concrete implementations of these methods to handle authentication-related logic.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Instantiate or inject AuthenticationService
 * AuthenticationService authService = // instantiate or inject the implementation
 *
 * // Create an AuthenticationRequest with user credentials
 * AuthenticationRequest loginRequest = // create an instance of AuthenticationRequest
 *
 * // Perform user authentication
 * AuthenticationResponse authenticationResponse = authService.login(loginRequest);
 *
 * // Retrieve the ID of the authenticated user
 * Long authenticatedUserId = authService.getUserAuthenticated();
 * }
 * </pre>
 *
 * <p>Implementing classes should handle {@link BusinessException} to appropriately manage exceptions
 * related to authentication operations.</p>
 *
 * <p>Instances of this interface are typically used in the security layer of the application to provide
 * authentication-related functionality.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface AuthenticationService {
    /**
     * Login method to authenticate a user.
     *
     * @param request The authentication request containing user credentials
     * @return The authentication response, including authentication status and tokens
     */
    AuthenticationResponse login(AuthenticationRequest request);

    /**
     * Method that returns the ID of the user who is authenticated.
     *
     * @return The ID of the authenticated user
     * @throws BusinessException If an error occurs while retrieving the authenticated user ID
     */
    Long getUserAuthenticated() throws BusinessException;
}
