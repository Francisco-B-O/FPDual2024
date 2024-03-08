package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BOToDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO userBOToDTO(UserBO userBO) {
        return modelMapper.map(userBO, UserDTO.class);
    }

    public RoleDTO roleBOToDTO(RoleBO roleBO) {
        return modelMapper.map(roleBO, RoleDTO.class);
    }

    public ModalityDTO modalityBOToDTO(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, ModalityDTO.class);
    }

    public TeamDTO teamBOToDTO(TeamBO teamBO) {
        return modelMapper.map(teamBO, TeamDTO.class);
    }

    public FormatDTO formatBOToDTO(FormatBO formatBO) {
        return modelMapper.map(formatBO, FormatDTO.class);
    }

    public TournamentDTO tournamentBOToDTO(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, TournamentDTO.class);
    }

    public UserDTOAux userBOAuxToDTOAux(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, UserDTOAux.class);
    }

    public TournamentDTOAux tournamentBOAuxToDTOAux(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, TournamentDTOAux.class);
    }
}
