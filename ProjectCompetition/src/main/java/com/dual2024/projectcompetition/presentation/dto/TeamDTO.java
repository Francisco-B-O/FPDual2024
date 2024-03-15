package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * DTO representing a team.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = " DTO representing a team")
public class TeamDTO {
    @Schema(description = "Unique identifier of the team", example = "1")
    private Long id;

    @Schema(description = "Name of the team", example = "Team A")
    private String name;

    @Schema(description = "Modality of the team")
    private ModalityDTO modality;

    @Schema(description = "Path to the team's logo", example = "logo.png")
    private String logo;

    @Schema(description = "Captain's unique identifier", example = "1")
    private Long captainId;

    @Schema(description = "List of users in the team")
    private List<UserDTOAux> users;

    @Schema(description = "List of tournaments the team is participating in")
    private List<TournamentDTOAux> tournaments;
}
