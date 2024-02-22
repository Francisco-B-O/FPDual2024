package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import java.util.List;

import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

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

	private UserState state;

	private List<RoleBO> roles;

	private List<TeamBOAux> teams;

	public boolean isInActiveTournament() {
		boolean isInActiveTournament = false;
		for (TeamBOAux team : this.teams) {
			for (TournamentBOAux tournament : team.getTournaments()) {
				if (tournament.getState().equals(TournamentState.EN_JUEGO)) {
					isInActiveTournament = true;
				}
			}
		}
		return isInActiveTournament;
	}
}
