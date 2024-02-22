package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TeamBOAux {
	private Long id;

	private String name;

	private ModalityBO modality;

	private String logo;

	private List<TournamentBOAux> tournaments;

	private UserBOAux captain;

	private List<UserBOAux> users;
}
