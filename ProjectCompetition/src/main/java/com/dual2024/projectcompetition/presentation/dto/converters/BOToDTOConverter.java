package com.dual2024.projectcompetition.presentation.dto.converters;

import com.dual2024.projectcompetition.business.businessobject.*;
import com.dual2024.projectcompetition.presentation.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converter class for mapping Business Objects (BOs) to Data Transfer Objects (DTOs).
 *
 * <p>This class uses the ModelMapper library for automatic mapping of BO fields to corresponding
 * fields in DTOs. Each conversion method in this class is responsible for converting a specific type
 * of BO to its corresponding DTO.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * BOToDTOConverter converter = new BOToDTOConverter();
 * UserDTO userDTO = converter.userBOToDTO(userBO);
 * }
 * </pre>
 *
 * <p>The conversion methods return instances of DTOs with fields mapped from the provided BO.</p>
 *
 * @author Franciosco Balonero Olivera
 */
@Component
public class BOToDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a UserBO to a UserDTO.
     *
     * @param userBO The UserBO
     * @return The corresponding UserDTO
     */
    public UserDTO userBOToDTO(UserBO userBO) {
        return modelMapper.map(userBO, UserDTO.class);
    }

    /**
     * Converts a RoleBO to a RoleDTO.
     *
     * @param roleBO The RoleBO
     * @return The corresponding RoleDTO
     */
    public RoleDTO roleBOToDTO(RoleBO roleBO) {
        return modelMapper.map(roleBO, RoleDTO.class);
    }

    /**
     * Converts a ModalityBO to a ModalityDTO.
     *
     * @param modalityBO The ModalityBO
     * @return The corresponding ModalityDTO
     */
    public ModalityDTO modalityBOToDTO(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, ModalityDTO.class);
    }

    /**
     * Converts a TeamBO to a TeamDTO.
     *
     * @param teamBO The TeamBO
     * @return The corresponding TeamDTO
     */
    public TeamDTO teamBOToDTO(TeamBO teamBO) {
        return modelMapper.map(teamBO, TeamDTO.class);
    }

    /**
     * Converts a FormatBO to a FormatDTO.
     *
     * @param formatBO The FormatBO
     * @return The corresponding FormatDTO
     */
    public FormatDTO formatBOToDTO(FormatBO formatBO) {
        return modelMapper.map(formatBO, FormatDTO.class);
    }

    /**
     * Converts a TournamentBO to a TournamentDTO.
     *
     * @param tournamentBO The TournamentBO
     * @return The corresponding TournamentDTO
     */
    public TournamentDTO tournamentBOToDTO(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, TournamentDTO.class);
    }

    /**
     * Converts a UserBOAux to a UserDTOAux.
     *
     * @param userBOAux The UserBOAux
     * @return The corresponding UserDTOAux
     */
    public UserDTOAux userBOAuxToDTOAux(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, UserDTOAux.class);
    }

    /**
     * Converts a TournamentBOAux to a TournamentDTOAux.
     *
     * @param tournamentBOAux The TournamentBOAux
     * @return The corresponding TournamentDTOAux
     */
    public TournamentDTOAux tournamentBOAuxToDTOAux(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, TournamentDTOAux.class);
    }
}
