package com.dual2024.projectcompetition.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Authentication request.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
