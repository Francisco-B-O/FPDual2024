package com.dual2024.projectcompetition.presentation.dto;

import com.dual2024.projectcompetition.utils.TournamentState;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Register tournament dto.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegisterTournamentDTO {
    @NotBlank
    private String name;

    @Min(2)
    private int size;

    @NotNull
    private FormatDTO format;

    @FutureOrPresent
    private LocalDateTime startDate;

    @Future
    private LocalDateTime endDate;

    @NotNull
    private TournamentState state;

    private String logo;

    @NotBlank
    private String description;

    @NotNull
    private ModalityDTO modality;
}
