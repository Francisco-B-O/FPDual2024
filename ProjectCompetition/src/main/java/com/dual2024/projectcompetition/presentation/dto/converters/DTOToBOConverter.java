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
     * @param userDTO The UserDTO
     * @return The corresponding UserBO
     */
    public UserBO userDTOToBO(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    /**
     * Converts a RoleDTO to a RoleBO.
     *
     * @param roleDTO The RoleDTO
     * @return The corresponding RoleBO
     */
    public RoleBO roleDTOToBO(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, RoleBO.class);
    }

    /**
     * Converts a ModalityDTO to a ModalityBO.
     *
     * @param modalityDTO The ModalityDTO
     * @return The corresponding ModalityBO
     */
    public ModalityBO modalityDTOToBO(ModalityDTO modalityDTO) {
        return modelMapper.map(modalityDTO, ModalityBO.class);
    }

    /**
     * Converts a TeamDTO to a TeamBO.
     *
     * @param teamDTO The TeamDTO
     * @return The corresponding TeamBO
     */
    public TeamBO teamDTOToBO(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    /**
     * Converts a FormatDTO to a FormatBO.
     *
     * @param formatDTO The FormatDTO
     * @return The corresponding FormatBO
     */
    public FormatBO formatDTOToBO(FormatDTO formatDTO) {
        return modelMapper.map(formatDTO, FormatBO.class);
    }

    /**
     * Converts a TournamentDTO to a TournamentBO.
     *
     * @param tournamentDTO The TournamentDTO
     * @return The corresponding TournamentBO
     */
    public TournamentBO tournamentDTOToBO(TournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

    /**
     * Converts a UserDTOAux to a UserBOAux.
     *
     * @param userDTO The UserDTOAux
     * @return The corresponding UserBOAux
     */
    public UserBOAux userDTOAuxToBOAux(UserDTOAux userDTO) {
        return modelMapper.map(userDTO, UserBOAux.class);
    }

    /**
     * Converts a TournamentDTOAux to a TournamentBOAux.
     *
     * @param tournamentDTO The TournamentDTOAux
     * @return The corresponding TournamentBOAux
     */
    public TournamentBOAux tournamentDTOAuxToBOAux(TournamentDTOAux tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBOAux.class);
    }

    /**
     * Converts a RegisterUserDTO to a UserBO.
     *
     * @param userDTO The RegisterUserDTO
     * @return The corresponding UserBO
     */
    public UserBO RegisterUserDTOToBO(RegisterUserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    /**
     * Converts a RegisterTeamDTO to a TeamBO.
     *
     * @param teamDTO The RegisterTeamDTO
     * @return The corresponding TeamBO
     */
    public TeamBO RegisterTeamDTOToBO(RegisterTeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    /**
     * Converts a RegisterTournamentDTO to a TournamentBO.
     *
     * @param tournamentDTO The RegisterTournamentDTO
     * @return The corresponding TournamentBO
     */
    public TournamentBO RegisterTournamentDTOToBO(RegisterTournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

    /**
     * Converts a RegisterModalityDTO to a ModalityBO.
     *
     * @param modalityDTO The RegisterModalityDTO
     * @return The corresponding ModalityBO
     */
    public ModalityBO RegisterModalityDTOToBO(RegisterModalityDTO modalityDTO) {
        return modelMapper.map(modalityDTO, ModalityBO.class);
    }

    /**
     * Converts a RegisterFormatDTO to a FormatBO.
     *
     * @param formatDTO The RegisterFormatDTO
     * @return The corresponding FormatBO
     */
    public FormatBO RegisterFormatDTOToBO(RegisterFormatDTO formatDTO) {
        return modelMapper.map(formatDTO, FormatBO.class);
    }

}
