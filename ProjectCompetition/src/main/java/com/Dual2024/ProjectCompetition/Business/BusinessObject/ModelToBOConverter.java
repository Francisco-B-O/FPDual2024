package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;

@Component
public class ModelToBOConverter {
	@Autowired
	private ModelMapper modelMapper;

	public UserBO userModelToBO(User user) {
		UserBO userBO = modelMapper.map(user, UserBO.class);
		return userBO;
	}

	public RoleBO roleModelToBO(Role role) {
		RoleBO roleBO = modelMapper.map(role, RoleBO.class);
		return roleBO;
	}

	public ModalityBO modalityModelToBO(Modality modality) {
		ModalityBO modalityBO = modelMapper.map(modality, ModalityBO.class);
		return modalityBO;
	}

	public TeamBO teamModelToBO(Team team) {
		TeamBO teamBO = modelMapper.map(team, TeamBO.class);
		return teamBO;
	}

	public FormatBO formatModelToBO(Format format) {
		FormatBO formatBO = modelMapper.map(format, FormatBO.class);
		return formatBO;
	}

	public TournamentBO tournamentModelToBO(Tournament tournament) {
		TournamentBO tournamentBO = modelMapper.map(tournament, TournamentBO.class);
		return tournamentBO;
	}

	public UserBOAux userModelToBOAux(User user) {
		UserBOAux userBOAux = modelMapper.map(user, UserBOAux.class);
		return userBOAux;
	}

	public TournamentBOAux tournamentModelToBOAux(Tournament tournament) {
		TournamentBOAux tournamentBOAux = modelMapper.map(tournament, TournamentBOAux.class);
		return tournamentBOAux;
	}
}
