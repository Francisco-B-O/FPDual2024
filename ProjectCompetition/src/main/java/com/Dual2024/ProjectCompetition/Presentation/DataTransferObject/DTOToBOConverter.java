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
public class DTOToBOConverter {
	@Autowired
	private ModelMapper modelMapper;

	public UserBO userDTOToBO(UserDTO userDTO) {
		UserBO userBO = modelMapper.map(userDTO, UserBO.class);
		return userBO;
	}

	public RoleBO roleDTOToBO(RoleDTO roleDTO) {
		RoleBO roleBO = modelMapper.map(roleDTO, RoleBO.class);
		return roleBO;
	}

	public ModalityBO modalityDTOToBO(ModalityDTO modalityDTO) {
		ModalityBO modalityBO = modelMapper.map(modalityDTO, ModalityBO.class);
		return modalityBO;
	}

	public TeamBO teamDTOToBO(TeamDTO teamDTO) {
		TeamBO teamBO = modelMapper.map(teamDTO, TeamBO.class);
		return teamBO;
	}

	public FormatBO formatDTOToBO(FormatDTO formatDTO) {
		FormatBO formatBO = modelMapper.map(formatDTO, FormatBO.class);
		return formatBO;
	}

	public TournamentBO tournamentDTOToBO(TournamentDTO tournamentDTO) {
		TournamentBO tournamentBO = modelMapper.map(tournamentDTO, TournamentBO.class);
		return tournamentBO;
	}

	public UserBOAux userDTOAuxToBOAux(UserDTOAux userDTO) {
		UserBOAux userBOAux = modelMapper.map(userDTO, UserBOAux.class);
		return userBOAux;
	}

	public TeamBOAux teamDTOAuxToBOAux(TeamDTOAux teamDTO) {
		TeamBOAux teamBOAux = modelMapper.map(teamDTO, TeamBOAux.class);
		return teamBOAux;
	}

	public TournamentBOAux tournamentDTOAuxToBOAux(TournamentDTOAux tournamentDTO) {
		TournamentBOAux tournamentBOAux = modelMapper.map(tournamentDTO, TournamentBOAux.class);
		return tournamentBOAux;
	}

	public UserBO RegisterUserDTOToBO(RegisterUserDTO userDTO) {
		UserBO userBO = modelMapper.map(userDTO, UserBO.class);
		return userBO;
	}

	public TeamBO RegisterTeamDTOToBO(RegisterTeamDTO teamDTO) {
		TeamBO teamBO = modelMapper.map(teamDTO, TeamBO.class);
		return teamBO;
	}

	public TournamentBO RegisterTournamentDTOToBO(RegisterTournamentDTO tournamentDTO) {
		TournamentBO tournamentBO = modelMapper.map(tournamentDTO, TournamentBO.class);
		return tournamentBO;
	}

}
