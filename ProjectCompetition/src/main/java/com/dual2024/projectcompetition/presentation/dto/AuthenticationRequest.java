package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO representing an authentication request.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@Schema(description = "DTO representing an authentication request")
public class AuthenticationRequest {
    @Schema(description = "User's email address", requiredMode = Schema.RequiredMode.REQUIRED, example = "user@example.com")
    private String email;

    @Schema(description = "User's password", requiredMode = Schema.RequiredMode.REQUIRED, example = "mysupersecretpassword")
    private String password;
}
