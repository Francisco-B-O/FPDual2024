package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;

@Component
public class BOToDTOConverter {
	@Autowired
	private ModelMapper modelMapper;

	public UserDTO userBOToDTO(UserBO userBO) {
		UserDTO user = modelMapper.map(userBO, UserDTO.class);
		return user;
	}

	public RoleDTO roleBOToDTO(RoleBO roleBO) {
		RoleDTO role = modelMapper.map(roleBO, RoleDTO.class);
		return role;
	}

	public ModalityDTO modalityBOToDTO(ModalityBO modalityBO) {
		ModalityDTO modality = modelMapper.map(modalityBO, ModalityDTO.class);
		return modality;
	}

	public TeamDTO teamBOToDTO(TeamBO teamBO) {
		TeamDTO team = modelMapper.map(teamBO, TeamDTO.class);
		return team;
	}

	public FormatDTO formatBOToDTO(FormatBO formatBO) {
		FormatDTO format = modelMapper.map(formatBO, FormatDTO.class);
		return format;
	}

	public TournamentDTO tournamentBOToDTO(TournamentBO tournamentBO) {
		TournamentDTO tournament = modelMapper.map(tournamentBO, TournamentDTO.class);
		return tournament;
	}

	public UserDTOAux userBOAuxToDTOAux(UserBOAux userBOAux) {
		UserDTOAux user = modelMapper.map(userBOAux, UserDTOAux.class);
		return user;
	}

	public TeamDTOAux teamBOAuxToDTOAux(TeamBOAux teamBOAux) {
		TeamDTOAux team = modelMapper.map(teamBOAux, TeamDTOAux.class);
		return team;
	}

	public TournamentDTOAux tournamentBOAuxToDTOAux(TournamentBOAux tournamentBOAux) {
		TournamentDTOAux tournament = modelMapper.map(tournamentBOAux, TournamentDTOAux.class);
		return tournament;
	}
}
