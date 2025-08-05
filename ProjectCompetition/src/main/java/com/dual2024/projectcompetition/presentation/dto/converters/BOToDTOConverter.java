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
     * @param userBO The {@link UserBO} object to convert
     * @return The {@link UserDTO} object representing the converted user
     */
    public UserDTO userBOToDTO(UserBO userBO) {
        return modelMapper.map(userBO, UserDTO.class);
    }

    /**
     * Converts a RoleBO to a RoleDTO.
     *
     * @param roleBO The {@link RoleBO} object to convert
     * @return The {@link RoleDTO} object representing the converted role
     */
    public RoleDTO roleBOToDTO(RoleBO roleBO) {
        return modelMapper.map(roleBO, RoleDTO.class);
    }

    /**
     * Converts a ModalityBO to a ModalityDTO.
     *
     * @param modalityBO The {@link ModalityBO} object to convert
     * @return The {@link ModalityDTO} object representing the converted modality
     */
    public ModalityDTO modalityBOToDTO(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, ModalityDTO.class);
    }

    /**
     * Converts a TeamBO to a TeamDTO.
     *
     * @param teamBO The {@link TeamBO} object to convert
     * @return The {@link TeamDTO} object representing the converted team
     */
    public TeamDTO teamBOToDTO(TeamBO teamBO) {
        return modelMapper.map(teamBO, TeamDTO.class);
    }

    /**
     * Converts a FormatBO to a FormatDTO.
     *
     * @param formatBO The {@link FormatBO} object to convert
     * @return The {@link FormatDTO} object representing the converted format
     */
    public FormatDTO formatBOToDTO(FormatBO formatBO) {
        return modelMapper.map(formatBO, FormatDTO.class);
    }

    /**
     * Converts a TournamentBO to a TournamentDTO.
     *
     * @param tournamentBO The {@link TournamentBO} object to convert
     * @return The {@link TournamentDTO} object representing the converted tournament
     */
    public TournamentDTO tournamentBOToDTO(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, TournamentDTO.class);
    }

    /**
     * Converts a UserBOAux to a UserDTOAux.
     *
     * @param userBOAux The {@link UserBOAux} object to convert
     * @return The {@link UserDTOAux} object representing the converted user auxiliary information
     */
    public UserDTOAux userBOAuxToDTOAux(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, UserDTOAux.class);
    }

    /**
     * Converts a TournamentBOAux to a TournamentDTOAux.
     *
     * @param tournamentBOAux The {@link TournamentBOAux} object to convert
     * @return The {@link TournamentDTOAux} object representing the converted tournament auxiliary information
     */
    public TournamentDTOAux tournamentBOAuxToDTOAux(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, TournamentDTOAux.class);
    }
}
