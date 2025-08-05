package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO for registering a new competition modality.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "DTO for registering a new competition modality")
public class RegisterModalityDTO {
    @Schema(description = "Number of players in the modality", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "Number of players must be at least 1")
    private int numberPlayers;

    @Schema(description = "Name of the modality", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
