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
     * @param userBO the user bo
     * @return the user
     */
    public User userBOToModel(UserBO userBO) {
        return modelMapper.map(userBO, User.class);
    }

    /**
     * Role bo to model role.
     *
     * @param roleBO the role bo
     * @return the role
     */
    public Role roleBOToModel(RoleBO roleBO) {
        return modelMapper.map(roleBO, Role.class);
    }

    /**
     * Modality bo to model modality.
     *
     * @param modalityBO the modality bo
     * @return the modality
     */
    public Modality modalityBOToModel(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, Modality.class);
    }

    /**
     * Team bo to model team.
     *
     * @param teamBO the team bo
     * @return the team
     */
    public Team teamBOToModel(TeamBO teamBO) {
        return modelMapper.map(teamBO, Team.class);
    }

    /**
     * Format bo to model format.
     *
     * @param formatBO the format bo
     * @return the format
     */
    public Format formatBOToModel(FormatBO formatBO) {
        return modelMapper.map(formatBO, Format.class);
    }

    /**
     * Tournament bo to model tournament.
     *
     * @param tournamentBO the tournament bo
     * @return the tournament
     */
    public Tournament tournamentBOToModel(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, Tournament.class);
    }

    /**
     * User bo aux to model user.
     *
     * @param userBOAux the user bo aux
     * @return the user
     */
    public User userBOAuxToModel(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, User.class);
    }

    /**
     * Tournament bo aux to model tournament.
     *
     * @param tournamentBOAux the tournament bo aux
     * @return the tournament
     */
    public Tournament tournamentBOAuxToModel(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, Tournament.class);
    }

}
