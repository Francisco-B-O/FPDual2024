package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;

public class ModelToBOConverter {
	@Autowired
	private ModelMapper mapper;

	public UserBO userModelToBO(User user) {
		UserBO userBO = mapper.map(user, UserBO.class);
		return userBO;
	}

	public RoleBO roleModelToBO(Role role) {
		RoleBO roleBO = mapper.map(role, RoleBO.class);
		return roleBO;
	}

	public ModalityBO modalityModelToBO(Modality modality) {
		ModalityBO modalityBO = mapper.map(modality, ModalityBO.class);
		return modalityBO;
	}

	public TeamBO teamModelToBO(Team team) {
		TeamBO teamBO = mapper.map(team, TeamBO.class);
		return teamBO;
	}

	public FormatBO formatModelToBO(Format format) {
		FormatBO formatBO = mapper.map(format, FormatBO.class);
		return formatBO;
	}

	public TournamentBO tournamentModelToBO(Tournament tournament) {
		TournamentBO tournamentBO = mapper.map(tournament, TournamentBO.class);
		return tournamentBO;
	}
}
