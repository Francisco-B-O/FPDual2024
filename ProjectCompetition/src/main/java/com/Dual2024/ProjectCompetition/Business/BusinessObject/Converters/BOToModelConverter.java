package com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.*;
import com.Dual2024.ProjectCompetition.DataAccess.Model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converter class for mapping business objects to data entities.
 *
 * <p>This class uses the ModelMapper library for automatic mapping of business object fields to
 * corresponding fields in data entities. Each conversion method in this class is responsible
 * for converting a specific type of business object to its corresponding data entity.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * BOToModelConverter converter = new BOToModelConverter();
 * User userEntity = converter.userBOToModel(userBO);
 * }
 * </pre>
 *
 * <p>The conversion methods return instances of data entities with fields mapped from the
 * provided business object.</p>
 *
 * @author Franciosco Balonero Olivera
 */
@Component
public class BOToModelConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a UserBO to a User entity.
     *
     * @param userBO The UserBO
     * @return The corresponding User entity
     */
    public User userBOToModel(UserBO userBO) {
        return modelMapper.map(userBO, User.class);
    }

    /**
     * Converts a RoleBO to a Role entity.
     *
     * @param roleBO The RoleBO
     * @return The corresponding Role entity
     */
    public Role roleBOToModel(RoleBO roleBO) {
        return modelMapper.map(roleBO, Role.class);
    }

    /**
     * Converts a ModalityBO to a Modality entity.
     *
     * @param modalityBO The ModalityBO
     * @return The corresponding Modality entity
     */
    public Modality modalityBOToModel(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, Modality.class);
    }

    /**
     * Converts a TeamBO to a Team entity.
     *
     * @param teamBO The TeamBO
     * @return The corresponding Team entity
     */
    public Team teamBOToModel(TeamBO teamBO) {
        return modelMapper.map(teamBO, Team.class);
    }

    /**
     * Converts a FormatBO to a Format entity.
     *
     * @param formatBO The FormatBO
     * @return The corresponding Format entity
     */
    public Format formatBOToModel(FormatBO formatBO) {
        return modelMapper.map(formatBO, Format.class);
    }

    /**
     * Converts a TournamentBO to a Tournament entity.
     *
     * @param tournamentBO The TournamentBO
     * @return The corresponding Tournament entity
     */
    public Tournament tournamentBOToModel(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, Tournament.class);
    }

    /**
     * Converts a UserBOAux to a User entity.
     *
     * @param userBOAux The UserBOAux
     * @return The corresponding User entity
     */
    public User userBOAuxToModel(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, User.class);
    }

    /**
     * Converts a TournamentBOAux to a Tournament entity.
     *
     * @param tournamentBOAux The TournamentBOAux
     * @return The corresponding Tournament entity
     */
    public Tournament tournamentBOAuxToModel(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, Tournament.class);
    }
}
