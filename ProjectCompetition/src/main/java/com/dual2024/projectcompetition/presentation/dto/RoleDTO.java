package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO representing a user role.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "DTO representing a user role")
public class RoleDTO {
    @Schema(description = "Unique identifier of the role", example = "1")
    private Long id;

    @Schema(description = "Name of the role", example = "ADMIN")
    private String name;

    @Schema(description = "Description of the role", example = "Administrator privileges")
    private String description;
}
