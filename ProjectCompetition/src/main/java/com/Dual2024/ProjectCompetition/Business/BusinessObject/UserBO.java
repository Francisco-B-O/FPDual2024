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
public class UserBO {

	private Long id;

	private String nick;

	private String email;

	private String password;

	private String avatar;

	private UserStateBO state;

	private List<RoleBO> roles;

	private List<TeamBOAux> teams;

	public boolean isInActiveTournament() {
		boolean isInActiveTournament = false;
		for (TeamBOAux team : this.teams) {
			for (TournamentBOAux tournament : team.getTournaments()) {
				if (tournament.getState().equals(TournamentStateBO.EN_JUEGO)) {
					isInActiveTournament = true;
				}
			}
		}
		return isInActiveTournament;
	}
}
