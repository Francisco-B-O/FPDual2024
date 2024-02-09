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
	private ModelMapper mapper;

	public User userBOToModel(UserBO userBO) {
		User user = mapper.map(userBO, User.class);
		return user;
	}

	public Role roleBOToModel(UserBO roleBO) {
		Role role = mapper.map(roleBO, Role.class);
		return role;
	}

	public Modality modalityBOToModel(ModalityBO modalityBO) {
		Modality modality = mapper.map(modalityBO, Modality.class);
		return modality;
	}

	public Team teamBOToModel(TeamBO teamBO) {
		Team team = mapper.map(teamBO, Team.class);
		return team;
	}

	public Format formatBOToModel(FormatBO formatBO) {
		Format format = mapper.map(formatBO, Format.class);
		return format;
	}

	public Tournament tournamentBOToModel(TournamentBO tournamentBO) {
		Tournament tournament = mapper.map(tournamentBO, Tournament.class);
		return tournament;
	}

}
