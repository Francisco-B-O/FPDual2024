package com.Dual2024.ProjectCompetition.business.business_object.converters;

import com.Dual2024.ProjectCompetition.DataAccess.model.*;
import com.Dual2024.ProjectCompetition.business.business_object.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converter class for mapping data entities to business objects.
 *
 * <p>This class uses the ModelMapper library for automatic mapping of entity fields to
 * corresponding fields in business objects. Each conversion method in this class is responsible
 * for converting a specific type of entity to its corresponding business object.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * ModelToBOConverter converter = new ModelToBOConverter();
 * UserBO userBO = converter.userModelToBO(userEntity);
 * }
 * </pre>
 *
 * <p>The conversion methods return instances of business objects with fields mapped from the
 * provided entity.</p>
 *
 * @author Franciosco Balonero Olivera
 */
@Component
public class ModelToBOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a User entity to a UserBO.
     *
     * @param user The user entity
     * @return The corresponding UserBO
     */
    public UserBO userModelToBO(User user) {
        return modelMapper.map(user, UserBO.class);
    }

    /**
     * Converts a Role entity to a RoleBO.
     *
     * @param role The role entity
     * @return The corresponding RoleBO
     */
    public RoleBO roleModelToBO(Role role) {
        return modelMapper.map(role, RoleBO.class);
    }

    /**
     * Converts a Modality entity to a ModalityBO.
     *
     * @param modality The modality entity
     * @return The corresponding ModalityBO
     */
    public ModalityBO modalityModelToBO(Modality modality) {
        return modelMapper.map(modality, ModalityBO.class);
    }

    /**
     * Converts a Team entity to a TeamBO.
     *
     * @param team The team entity
     * @return The corresponding TeamBO
     */
    public TeamBO teamModelToBO(Team team) {
        return modelMapper.map(team, TeamBO.class);
    }

    /**
     * Converts a Format entity to a FormatBO.
     *
     * @param format The format entity
     * @return The corresponding FormatBO
     */
    public FormatBO formatModelToBO(Format format) {
        return modelMapper.map(format, FormatBO.class);
    }

    /**
     * Converts a Tournament entity to a TournamentBO.
     *
     * @param tournament The tournament entity
     * @return The corresponding TournamentBO
     */
    public TournamentBO tournamentModelToBO(Tournament tournament) {
        return modelMapper.map(tournament, TournamentBO.class);
    }

    /**
     * Converts a User entity to a UserBOAux.
     *
     * @param user The user entity
     * @return The corresponding UserBOAux
     */
    public UserBOAux userModelToBOAux(User user) {
        return modelMapper.map(user, UserBOAux.class);
    }

    /**
     * Converts a Tournament entity to a TournamentBOAux.
     *
     * @param tournament The tournament entity
     * @return The corresponding TournamentBOAux
     */
    public TournamentBOAux tournamentModelToBOAux(Tournament tournament) {
        return modelMapper.map(tournament, TournamentBOAux.class);
    }
}
