package com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.*;
import com.Dual2024.ProjectCompetition.DataAccess.Model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Business Object to Model converter.
 */
@Component
public class BOToModelConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * User bo to model user.
     *
     * @param userBO The user bo
     * @return The user
     */
    public User userBOToModel(UserBO userBO) {
        return modelMapper.map(userBO, User.class);
    }

    /**
     * Role bo to model role.
     *
     * @param roleBO The role bo
     * @return The role
     */
    public Role roleBOToModel(RoleBO roleBO) {
        return modelMapper.map(roleBO, Role.class);
    }

    /**
     * Modality bo to model modality.
     *
     * @param modalityBO The modality bo
     * @return The modality
     */
    public Modality modalityBOToModel(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, Modality.class);
    }

    /**
     * Team bo to model team.
     *
     * @param teamBO The team bo
     * @return The team
     */
    public Team teamBOToModel(TeamBO teamBO) {
        return modelMapper.map(teamBO, Team.class);
    }

    /**
     * Format bo to model format.
     *
     * @param formatBO The format bo
     * @return The format
     */
    public Format formatBOToModel(FormatBO formatBO) {
        return modelMapper.map(formatBO, Format.class);
    }

    /**
     * Tournament bo to model tournament.
     *
     * @param tournamentBO The tournament bo
     * @return The tournament
     */
    public Tournament tournamentBOToModel(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, Tournament.class);
    }

    /**
     * User bo aux to model user.
     *
     * @param userBOAux The user bo aux
     * @return The user
     */
    public User userBOAuxToModel(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, User.class);
    }

    /**
     * Tournament bo aux to model tournament.
     *
     * @param tournamentBOAux The tournament bo aux
     * @return The tournament
     */
    public Tournament tournamentBOAuxToModel(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, Tournament.class);
    }

}
