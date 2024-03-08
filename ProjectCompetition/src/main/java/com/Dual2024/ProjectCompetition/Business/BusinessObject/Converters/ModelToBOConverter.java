package com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.*;
import com.Dual2024.ProjectCompetition.DataAccess.Model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelToBOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserBO userModelToBO(User user) {
        return modelMapper.map(user, UserBO.class);
    }

    public RoleBO roleModelToBO(Role role) {
        return modelMapper.map(role, RoleBO.class);
    }

    public ModalityBO modalityModelToBO(Modality modality) {
        return modelMapper.map(modality, ModalityBO.class);
    }

    public TeamBO teamModelToBO(Team team) {
        return modelMapper.map(team, TeamBO.class);
    }

    public FormatBO formatModelToBO(Format format) {
        return modelMapper.map(format, FormatBO.class);
    }

    public TournamentBO tournamentModelToBO(Tournament tournament) {
        return modelMapper.map(tournament, TournamentBO.class);
    }

    public UserBOAux userModelToBOAux(User user) {
        return modelMapper.map(user, UserBOAux.class);
    }

    public TournamentBOAux tournamentModelToBOAux(Tournament tournament) {
        return modelMapper.map(tournament, TournamentBOAux.class);
    }
}
