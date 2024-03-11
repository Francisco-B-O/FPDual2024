package com.dual2024.projectcompetition.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Authentication response.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}
