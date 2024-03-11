package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

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
