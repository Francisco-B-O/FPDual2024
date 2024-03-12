package com.dual2024.projectcompetition.presentation.dto.converters;

import com.dual2024.projectcompetition.business.businessobject.*;
import com.dual2024.projectcompetition.presentation.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converter class for mapping Data Transfer Objects (DTOs) to Business Objects.
 *
 * <p>This class uses the ModelMapper library for automatic mapping of DTOs fields to
 * corresponding fields in business objects. Each conversion method in this class is responsible
 * for converting a specific type of data transfer object to its corresponding business object.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * DTOToBOConverter converter = new DTOToBOConverter();
 * UserBO userBO = converter.userDTOToBO(userDTO);
 * }
 * </pre>
 *
 * <p>The conversion methods return instances of BOs with fields mapped from the provided DTO.</p>
 *
 * @author Franciosco Balonero Olivera
 */
@Component
public class DTOToBOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a UserDTO to a UserBO.
     *
     * @param userDTO The {@link UserDTO} object to convert
     * @return The {@link UserBO} object representing the converted user
     */
    public UserBO userDTOToBO(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    /**
     * Converts a RoleDTO to a RoleBO.
     *
     * @param roleDTO The {@link RoleDTO} object to convert
     * @return The {@link RoleBO} object representing the converted role
     */
    public RoleBO roleDTOToBO(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, RoleBO.class);
    }

    /**
     * Converts a ModalityDTO to a ModalityBO.
     *
     * @param modalityDTO The {@link ModalityDTO} object to convert
     * @return The {@link ModalityBO} object representing the converted modality
     */
    public ModalityBO modalityDTOToBO(ModalityDTO modalityDTO) {
        return modelMapper.map(modalityDTO, ModalityBO.class);
    }

    /**
     * Converts a TeamDTO to a TeamBO.
     *
     * @param teamDTO The {@link TeamDTO} object to convert
     * @return The {@link TeamBO} object representing the converted team
     */
    public TeamBO teamDTOToBO(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    /**
     * Converts a FormatDTO to a FormatBO.
     *
     * @param formatDTO The {@link FormatDTO} object to convert
     * @return The {@link FormatBO} object representing the converted format
     */
    public FormatBO formatDTOToBO(FormatDTO formatDTO) {
        return modelMapper.map(formatDTO, FormatBO.class);
    }

    /**
     * Converts a TournamentDTO to a TournamentBO.
     *
     * @param tournamentDTO The {@link TournamentDTO} object to convert
     * @return The {@link TournamentBO} object representing the converted tournament
     */
    public TournamentBO tournamentDTOToBO(TournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

    /**
     * Converts a UserDTOAux to a UserBOAux.
     *
     * @param userDTO The {@link UserDTOAux} object to convert
     * @return The {@link UserBOAux} object representing the converted user auxiliary information
     */
    public UserBOAux userDTOAuxToBOAux(UserDTOAux userDTO) {
        return modelMapper.map(userDTO, UserBOAux.class);
    }

    /**
     * Converts a TournamentDTOAux to a TournamentBOAux.
     *
     * @param tournamentDTO The {@link TournamentDTOAux} object to convert
     * @return The {@link TournamentBOAux} object representing the converted tournament auxiliary information
     */
    public TournamentBOAux tournamentDTOAuxToBOAux(TournamentDTOAux tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBOAux.class);
    }

    /**
     * Converts a RegisterUserDTO to a UserBO.
     *
     * @param userDTO The {@link RegisterUserDTO} object to convert
     * @return The {@link UserBO} object representing the converted user
     */
    public UserBO RegisterUserDTOToBO(RegisterUserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    /**
     * Converts a RegisterTeamDTO to a TeamBO.
     *
     * @param teamDTO The {@link RegisterTeamDTO} object to convert
     * @return The {@link TeamBO} object representing the converted team
     */
    public TeamBO RegisterTeamDTOToBO(RegisterTeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    /**
     * Converts a RegisterTournamentDTO to a TournamentBO.
     *
     * @param tournamentDTO The {@link RegisterTournamentDTO} object to convert
     * @return The {@link TournamentBO} object representing the converted tournament
     */
    public TournamentBO RegisterTournamentDTOToBO(RegisterTournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

    /**
     * Converts a RegisterModalityDTO to a ModalityBO.
     *
     * @param modalityDTO The {@link RegisterModalityDTO} object to convert
     * @return The {@link ModalityBO} object representing the converted modality
     */
    public ModalityBO RegisterModalityDTOToBO(RegisterModalityDTO modalityDTO) {
        return modelMapper.map(modalityDTO, ModalityBO.class);
    }

    /**
     * Converts a RegisterFormatDTO to a FormatBO.
     *
     * @param formatDTO The {@link RegisterFormatDTO} object to convert
     * @return The {@link FormatBO} object representing the converted format
     */
    public FormatBO RegisterFormatDTOToBO(RegisterFormatDTO formatDTO) {
        return modelMapper.map(formatDTO, FormatBO.class);
    }
}
