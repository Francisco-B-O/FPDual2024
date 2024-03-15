package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO representing a competition modality.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "DTO representing a competition modality")
public class ModalityDTO {
    @Schema(description = "Unique identifier of the modality", example = "1")
    private Long id;

    @Schema(description = "Number of players in the modality", example = "11")
    private int numberPlayers;

    @Schema(description = "Name of the modality", example = "Soccer")
    private String name;
}
