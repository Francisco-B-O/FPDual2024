package com.dual2024.projectcompetition.presentation.dto;

import com.dual2024.projectcompetition.utils.TournamentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Tournament dto aux.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TournamentDTOAux {
    private Long id;

    private String name;

    private int size;

    private FormatDTO format;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private TournamentState state;

    private String logo;

    private String description;

    private ModalityDTO modality;
}
