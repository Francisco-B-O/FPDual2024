package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO for registering a new team.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = " DTO for registering a new team")
public class RegisterTeamDTO {
    @Schema(description = "Name of the team", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Modality of the team", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Modality cannot be null")
    private ModalityDTO modality;

    @Schema(description = "Path to the team's logo", example = "logo.png")
    private String logo;
}
