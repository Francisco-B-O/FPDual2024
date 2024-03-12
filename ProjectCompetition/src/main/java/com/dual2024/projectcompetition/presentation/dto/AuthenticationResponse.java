package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO representing an authentication response (Jwt).
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@Schema(description = " DTO representing an authentication response (Jwt)")
public class AuthenticationResponse {
    @Schema(description = "JWT token for authentication", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jwt;
}
