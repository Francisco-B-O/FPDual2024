package com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.*;
import com.Dual2024.ProjectCompetition.DataAccess.Model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BOToModelConverter {
    @Autowired
    private ModelMapper modelMapper;

    public User userBOToModel(UserBO userBO) {
        return modelMapper.map(userBO, User.class);
    }

    public Role roleBOToModel(RoleBO roleBO) {
        return modelMapper.map(roleBO, Role.class);
    }

    public Modality modalityBOToModel(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, Modality.class);
    }

    public Team teamBOToModel(TeamBO teamBO) {
        return modelMapper.map(teamBO, Team.class);
    }

    public Format formatBOToModel(FormatBO formatBO) {
        return modelMapper.map(formatBO, Format.class);
    }

    public Tournament tournamentBOToModel(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, Tournament.class);
    }

    public User userBOAuxToModel(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, User.class);
    }

    public Tournament tournamentBOAuxToModel(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, Tournament.class);
    }

}
