package com.dual2024.projectcompetition.presentation.dto;

import com.dual2024.projectcompetition.utils.TournamentState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Auxiliary DTO representing tournament information.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "Auxiliary DTO representing tournament information")
public class TournamentDTOAux {
    @Schema(description = "Unique identifier of the tournament", example = "1")
    private Long id;

    @Schema(description = "Name of the tournament", example = "Summer Cup")
    private String name;

    @Schema(description = "Size of the tournament", example = "16")
    private int size;

    @Schema(description = "Format of the tournament")
    private FormatDTO format;

    @Schema(description = "Start date of the tournament")
    private LocalDateTime startDate;

    @Schema(description = "End date of the tournament")
    private LocalDateTime endDate;

    @Schema(description = "State of the tournament")
    private TournamentState state;

    @Schema(description = "Path to the tournament's logo", example = "logo.png")
    private String logo;

    @Schema(description = "Description of the tournament")
    private String description;

    @Schema(description = "Modality of the tournament")
    private ModalityDTO modality;
}
