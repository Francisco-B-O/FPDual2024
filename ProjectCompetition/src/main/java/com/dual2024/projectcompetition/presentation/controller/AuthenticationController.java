package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.service.security.AuthenticationService;
import com.dual2024.projectcompetition.presentation.dto.AuthenticationRequest;
import com.dual2024.projectcompetition.presentation.dto.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication-related requests.
 *
 * <p>This class defines RESTful endpoints for user authentication. It is responsible for
 * processing login requests and returning authentication responses.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * POST /auth/authenticate
 * Request Body:
 * {
 *   "email": "user@example.com",
 *   "password": "password123"
 * }
 * Response Body:
 * {
 *   "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyQGVtYWlsLmNvbSIsImlhdCI6MTY0ODM2NDQ3MCwiZXhwIjoxNjQ4MzY2MDcwfQ.Sf3vJL7ZulZXd2JQkWv-ewFxT_pzJbPYRk9-Q9BK1_Q"
 * }
 * }
 * </pre>
 *
 * <p>The endpoints are secured, and the "/authenticate" endpoint is accessible to all users.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see AuthenticationService
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Operations related to authentication management")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Handles the login request and returns an authentication response.
     *
     * @param request The authentication request
     * @return ResponseEntity containing the authentication response
     */
    @Operation(summary = "Handles the login request and returns an authentication response")
    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.login(request);
        return ResponseEntity.ok(authenticationResponse);
    }
}
