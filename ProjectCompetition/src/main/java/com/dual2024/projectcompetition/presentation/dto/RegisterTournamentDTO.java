package com.dual2024.projectcompetition.presentation.dto;

import com.dual2024.projectcompetition.utils.TournamentState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for registering a new tournament.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "DTO for registering a new tournament")
public class RegisterTournamentDTO {
    @Schema(description = "Name of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Size of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 2, message = "Size must be at least 2")
    private int size;

    @Schema(description = "Format of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Format cannot be null")
    private FormatDTO format;

    @Schema(description = "Start date of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDateTime startDate;

    @Schema(description = "End date of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;

    @Schema(description = "State of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "State cannot be null")
    private TournamentState state;

    @Schema(description = "Path to the tournament's logo", nullable = true)
    private String logo;

    @Schema(description = "Description of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Schema(description = "Modality of the tournament", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Modality cannot be null")
    private ModalityDTO modality;
}
