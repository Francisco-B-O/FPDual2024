package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.Converters;

import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.*;
import com.Dual2024.ProjectCompetition.business.businessobject.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Bo to dto converter.
 *
 * @author Franciosco Balonero Olivera
 */
@Component
public class BOToDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * User bo to dto user dto.
     *
     * @param userBO The user bo
     * @return The user dto
     */
    public UserDTO userBOToDTO(UserBO userBO) {
        return modelMapper.map(userBO, UserDTO.class);
    }

    /**
     * Role bo to dto role dto.
     *
     * @param roleBO The role bo
     * @return The role dto
     */
    public RoleDTO roleBOToDTO(RoleBO roleBO) {
        return modelMapper.map(roleBO, RoleDTO.class);
    }

    /**
     * Modality bo to dto modality dto.
     *
     * @param modalityBO the modality bo
     * @return The modality dto
     */
    public ModalityDTO modalityBOToDTO(ModalityBO modalityBO) {
        return modelMapper.map(modalityBO, ModalityDTO.class);
    }

    /**
     * Team bo to dto team dto.
     *
     * @param teamBO the team bo
     * @return The team dto
     */
    public TeamDTO teamBOToDTO(TeamBO teamBO) {
        return modelMapper.map(teamBO, TeamDTO.class);
    }

    /**
     * Format bo to dto format dto.
     *
     * @param formatBO the format bo
     * @return The format dto
     */
    public FormatDTO formatBOToDTO(FormatBO formatBO) {
        return modelMapper.map(formatBO, FormatDTO.class);
    }

    /**
     * Tournament bo to dto tournament dto.
     *
     * @param tournamentBO the tournament bo
     * @return The tournament dto
     */
    public TournamentDTO tournamentBOToDTO(TournamentBO tournamentBO) {
        return modelMapper.map(tournamentBO, TournamentDTO.class);
    }

    /**
     * User bo aux to dto aux user dto aux.
     *
     * @param userBOAux the user bo aux
     * @return The user dto aux
     */
    public UserDTOAux userBOAuxToDTOAux(UserBOAux userBOAux) {
        return modelMapper.map(userBOAux, UserDTOAux.class);
    }

    /**
     * Tournament bo aux to dto aux tournament dto aux.
     *
     * @param tournamentBOAux the tournament bo aux
     * @return The tournament dto aux
     */
    public TournamentDTOAux tournamentBOAuxToDTOAux(TournamentBOAux tournamentBOAux) {
        return modelMapper.map(tournamentBOAux, TournamentDTOAux.class);
    }
}
