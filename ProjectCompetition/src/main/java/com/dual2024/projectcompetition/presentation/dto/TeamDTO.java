package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The Team dto.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TeamDTO {
    @NotNull
    private Long id;

    private String name;

    private ModalityDTO modality;

    private String logo;

    private Long captainId;

    private List<UserDTOAux> users;

    private List<TournamentDTOAux> tournaments;
}
