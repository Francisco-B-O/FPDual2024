package com.dual2024.projectcompetition.business.businessobject.converters;

import com.dual2024.projectcompetition.business.businessobject.*;
import com.dual2024.projectcompetition.dataaccess.model.*;
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
     * @param userBO {@link UserBO} The UserBO
     * @return {@link User} The corresponding User entity
     */
    public User userBOToModel(UserBO userBO) {
        return modelMapper.map(userBO, User.class);
    }

    /**
     * Converts a RoleBO to a Role entity.
     *
     * @param roleBO {@link RoleBO} The RoleBO
     * @return {@link Role} The corresponding Role entity
     */
    public Role roleBOToModel(RoleBO roleBO) {
        return modelMapper.map(roleBO, Role.class);
    }

    /**
     * Converts a ModalityBO to a Modality entity.
     *
     * @param modalityBO {@link ModalityBO} The ModalityBO
     * @return {@link Modality} The corresponding Modality entity
     */
    public Modality modalityBOToModel(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, Modality.class);
    }

    /**
     * Converts a TeamBO to a Team entity.
     *
     * @param teamBO {@link TeamBO} The TeamBO
     * @return {@link Team} The corresponding Team entity
     */
    public Team teamBOToModel(TeamBO teamBO) {
        return modelMapper.map(teamBO, Team.class);
    }

    /**
     * Converts a FormatBO to a Format entity.
     *
     * @param formatBO {@link FormatBO} The FormatBO
     * @return {@link Format} The corresponding Format entity
     */
    public Format formatBOToModel(FormatBO formatBO) {
        return modelMapper.map(formatBO, Format.class);
    }

    /**
     * Converts a TournamentBO to a Tournament entity.
     *
     * @param tournamentBO {@link TournamentBO} The TournamentBO
     * @return {@link Tournament} The corresponding Tournament entity
     */
    public Tournament tournamentBOToModel(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, Tournament.class);
    }

    /**
     * Converts a UserBOAux to a User entity.
     *
     * @param userBOAux {@link UserBOAux} The UserBOAux
     * @return {@link User} The corresponding User entity
     */
    public User userBOAuxToModel(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, User.class);
    }

    /**
     * Converts a TournamentBOAux to a Tournament entity.
     *
     * @param tournamentBOAux {@link TournamentBOAux} The TournamentBOAux
     * @return {@link Tournament} The corresponding Tournament entity
     */
    public Tournament tournamentBOAuxToModel(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, Tournament.class);
    }
}
