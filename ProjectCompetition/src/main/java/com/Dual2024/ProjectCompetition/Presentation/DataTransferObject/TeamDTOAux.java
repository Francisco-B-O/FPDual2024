package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TeamDTOAux {
	private Long id;

	private String name;

	private ModalityDTO modality;

	private String logo;
	private Long captainId;

	private List<TournamentDTOAux> tournaments;

	private List<UserDTOAux> users;
}
