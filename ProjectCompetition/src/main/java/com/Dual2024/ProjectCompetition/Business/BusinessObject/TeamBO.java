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
public class TeamBO {

	private Long id;

	private String name;

	private ModalityBO modality;

	private String logo;

	private List<UserBOAux> users;

	private List<TournamentBOAux> tournaments;

	public boolean isInActiveTournament() {
		boolean isInActiveTournament = false;
		for (TournamentBOAux tournament : this.tournaments) {
			if (tournament.getState().equals(TournamentStateBO.EN_JUEGO)) {
				isInActiveTournament = true;
			}
		}
		return isInActiveTournament;
	}

}
