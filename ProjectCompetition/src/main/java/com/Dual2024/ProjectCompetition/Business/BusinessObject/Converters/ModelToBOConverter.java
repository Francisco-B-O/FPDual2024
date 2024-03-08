package com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.*;
import com.Dual2024.ProjectCompetition.DataAccess.Model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Model to Business Object converter.
 */
@Component
public class ModelToBOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * User model to bo user bo.
     *
     * @param user the user
     * @return the user bo
     */
    public UserBO userModelToBO(User user) {
        return modelMapper.map(user, UserBO.class);
    }

    /**
     * Role model to bo role bo.
     *
     * @param role the role
     * @return the role bo
     */
    public RoleBO roleModelToBO(Role role) {
        return modelMapper.map(role, RoleBO.class);
    }

    /**
     * Modality model to bo modality bo.
     *
     * @param modality the modality
     * @return the modality bo
     */
    public ModalityBO modalityModelToBO(Modality modality) {
        return modelMapper.map(modality, ModalityBO.class);
    }

    /**
     * Team model to bo team bo.
     *
     * @param team the team
     * @return the team bo
     */
    public TeamBO teamModelToBO(Team team) {
        return modelMapper.map(team, TeamBO.class);
    }

    /**
     * Format model to bo format bo.
     *
     * @param format the format
     * @return the format bo
     */
    public FormatBO formatModelToBO(Format format) {
        return modelMapper.map(format, FormatBO.class);
    }

    /**
     * Tournament model to bo tournament bo.
     *
     * @param tournament the tournament
     * @return the tournament bo
     */
    public TournamentBO tournamentModelToBO(Tournament tournament) {
        return modelMapper.map(tournament, TournamentBO.class);
    }

    /**
     * User model to bo aux user bo aux.
     *
     * @param user the user
     * @return the user bo aux
     */
    public UserBOAux userModelToBOAux(User user) {
        return modelMapper.map(user, UserBOAux.class);
    }

    /**
     * Tournament model to bo aux tournament bo aux.
     *
     * @param tournament the tournament
     * @return the tournament bo aux
     */
    public TournamentBOAux tournamentModelToBOAux(Tournament tournament) {
        return modelMapper.map(tournament, TournamentBOAux.class);
    }
}
