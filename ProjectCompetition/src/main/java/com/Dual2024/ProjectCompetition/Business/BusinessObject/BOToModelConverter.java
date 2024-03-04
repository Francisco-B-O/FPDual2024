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
public class BOToModelConverter {
	@Autowired
	private ModelMapper modelMapper;

	public User userBOToModel(UserBO userBO) {
		User user = modelMapper.map(userBO, User.class);
		return user;
	}

	public Role roleBOToModel(RoleBO roleBO) {
		Role role = modelMapper.map(roleBO, Role.class);
		return role;
	}

	public Modality modalityBOToModel(ModalityBO modalityBO) {
		Modality modality = modelMapper.map(modalityBO, Modality.class);
		return modality;
	}

	public Team teamBOToModel(TeamBO teamBO) {
		Team team = modelMapper.map(teamBO, Team.class);
		return team;
	}

	public Format formatBOToModel(FormatBO formatBO) {
		Format format = modelMapper.map(formatBO, Format.class);
		return format;
	}

	public Tournament tournamentBOToModel(TournamentBO tournamentBO) {
		Tournament tournament = modelMapper.map(tournamentBO, Tournament.class);
		return tournament;
	}

	public User userBOAuxToModel(UserBOAux userBOAux) {
		User user = modelMapper.map(userBOAux, User.class);
		return user;
	}

	public Tournament tournamentBOAuxToModel(TournamentBOAux tournamentBOAux) {
		Tournament tournament = modelMapper.map(tournamentBOAux, Tournament.class);
		return tournament;
	}

}
