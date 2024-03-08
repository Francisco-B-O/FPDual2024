package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOToBOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserBO userDTOToBO(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    public RoleBO roleDTOToBO(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, RoleBO.class);
    }

    public ModalityBO modalityDTOToBO(ModalityDTO modalityDTO) {
        return modelMapper.map(modalityDTO, ModalityBO.class);
    }

    public TeamBO teamDTOToBO(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    public FormatBO formatDTOToBO(FormatDTO formatDTO) {
        return modelMapper.map(formatDTO, FormatBO.class);
    }

    public TournamentBO tournamentDTOToBO(TournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

    public UserBOAux userDTOAuxToBOAux(UserDTOAux userDTO) {
        return modelMapper.map(userDTO, UserBOAux.class);
    }

    public TournamentBOAux tournamentDTOAuxToBOAux(TournamentDTOAux tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBOAux.class);
    }

    public UserBO RegisterUserDTOToBO(RegisterUserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    public TeamBO RegisterTeamDTOToBO(RegisterTeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    public TournamentBO RegisterTournamentDTOToBO(RegisterTournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

}
