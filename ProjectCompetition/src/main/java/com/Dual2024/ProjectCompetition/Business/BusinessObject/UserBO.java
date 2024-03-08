package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import java.util.List;

import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import com.Dual2024.ProjectCompetition.Utils.UserState;

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

	private List<TeamBO> teams;

	public boolean isInActiveTournament() {
		return this.teams.stream().iterator().next().getTournaments().stream()
				.anyMatch(c -> c.getState().equals(TournamentState.EN_JUEGO));
	}

}
