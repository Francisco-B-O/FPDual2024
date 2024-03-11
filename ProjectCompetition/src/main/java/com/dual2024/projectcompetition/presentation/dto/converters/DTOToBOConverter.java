package com.dual2024.projectcompetition.presentation.dto.converters;

import com.dual2024.projectcompetition.business.businessobject.*;
import com.dual2024.projectcompetition.presentation.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The  Dto to bo converter.
 *
 * @author Franciosco Balonero Olivera
 */
@Component
public class DTOToBOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * User dto to bo user bo.
     *
     * @param userDTO The user dto
     * @return The user bo
     */
    public UserBO userDTOToBO(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    /**
     * Role dto to bo role bo.
     *
     * @param roleDTO The role dto
     * @return The role bo
     */
    public RoleBO roleDTOToBO(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, RoleBO.class);
    }

    /**
     * Modality dto to bo modality bo.
     *
     * @param modalityDTO The modality dto
     * @return The modality bo
     */
    public ModalityBO modalityDTOToBO(ModalityDTO modalityDTO) {
        return modelMapper.map(modalityDTO, ModalityBO.class);
    }

    /**
     * Team dto to bo team bo.
     *
     * @param teamDTO The team dto
     * @return The team bo
     */
    public TeamBO teamDTOToBO(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    /**
     * Format dto to bo format bo.
     *
     * @param formatDTO The format dto
     * @return The format bo
     */
    public FormatBO formatDTOToBO(FormatDTO formatDTO) {
        return modelMapper.map(formatDTO, FormatBO.class);
    }

    /**
     * Tournament dto to bo tournament bo.
     *
     * @param tournamentDTO The tournament dto
     * @return The tournament bo
     */
    public TournamentBO tournamentDTOToBO(TournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

    /**
     * User dto aux to bo aux user bo aux.
     *
     * @param userDTO The user dto
     * @return The user bo aux
     */
    public UserBOAux userDTOAuxToBOAux(UserDTOAux userDTO) {
        return modelMapper.map(userDTO, UserBOAux.class);
    }

    /**
     * Tournament dto aux to bo aux tournament bo aux.
     *
     * @param tournamentDTO The tournament dto
     * @return The tournament bo aux
     */
    public TournamentBOAux tournamentDTOAuxToBOAux(TournamentDTOAux tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBOAux.class);
    }

    /**
     * Register user dto to bo user bo.
     *
     * @param userDTO The user dto
     * @return The user bo
     */
    public UserBO RegisterUserDTOToBO(RegisterUserDTO userDTO) {
        return modelMapper.map(userDTO, UserBO.class);
    }

    /**
     * Register team dto to bo team bo.
     *
     * @param teamDTO The team dto
     * @return The team bo
     */
    public TeamBO RegisterTeamDTOToBO(RegisterTeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamBO.class);
    }

    /**
     * Register tournament dto to bo tournament bo.
     *
     * @param tournamentDTO The tournament dto
     * @return The tournament bo
     */
    public TournamentBO RegisterTournamentDTOToBO(RegisterTournamentDTO tournamentDTO) {
        return modelMapper.map(tournamentDTO, TournamentBO.class);
    }

    /**
     * Register modality dto to bo modality bo.
     *
     * @param modalityDTO The modality dto
     * @return The modality bo
     */
    public ModalityBO RegisterModalityDTOToBO(RegisterModalityDTO modalityDTO) {
        return modelMapper.map(modalityDTO, ModalityBO.class);
    }

    /**
     * Register format dto to bo format bo.
     *
     * @param formatDTO The format dto
     * @return The format bo
     */
    public FormatBO RegisterFormatDTOToBO(RegisterFormatDTO formatDTO) {
        return modelMapper.map(formatDTO, FormatBO.class);
    }

}
