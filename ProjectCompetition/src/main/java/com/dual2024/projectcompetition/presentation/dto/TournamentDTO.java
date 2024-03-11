package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Tournament dto.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TournamentDTO {
    @NotNull
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

    private List<TeamDTOAux> teams;
}
