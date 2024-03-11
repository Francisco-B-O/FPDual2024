package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

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
