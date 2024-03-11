package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.service.security.AuthenticationService;
import com.dual2024.projectcompetition.presentation.dto.AuthenticationRequest;
import com.dual2024.projectcompetition.presentation.dto.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Authentication controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Login response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse jwDTO = authenticationService.login(request);
        return ResponseEntity.ok(jwDTO);
    }

}
