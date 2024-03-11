package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.businessexception.*;
import com.dual2024.projectcompetition.business.businessobject.ModalityBO;
import com.dual2024.projectcompetition.business.businessobject.TeamBO;
import com.dual2024.projectcompetition.business.service.ModalityService;
import com.dual2024.projectcompetition.business.service.TeamService;
import com.dual2024.projectcompetition.business.service.security.AuthenticationService;
import com.dual2024.projectcompetition.presentation.dto.RegisterTeamDTO;
import com.dual2024.projectcompetition.presentation.dto.TeamDTO;
import com.dual2024.projectcompetition.presentation.dto.converters.BOToDTOConverter;
import com.dual2024.projectcompetition.presentation.dto.converters.DTOToBOConverter;
import com.dual2024.projectcompetition.presentation.exception.NotFoundException;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Team controller.
 *
 * @author Franciosco Balonero Olivera
 */
@RequestMapping("team")
@RestController
public class TeamController {
    /**
     * The Bo to dto converter.
     */
    @Autowired
    BOToDTOConverter boToDTOConverter;
    /**
     * The Dto to bo converter.
     */
    @Autowired
    DTOToBOConverter dtoToBOConverter;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ModalityService modalityService;
    /**
     * The Authentication service.
     */
    @Autowired
    AuthenticationService authenticationService;

    /**
     * Gets allteams.
     *
     * @return the allteams
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<TeamDTO> getAllteams() throws PresentationException {
        List<TeamDTO> listTeamDTO = new ArrayList<TeamDTO>();
        try {
            teamService.getAllTeams().forEach((TeamBO team) -> listTeamDTO.add(boToDTOConverter.teamBOToDTO(team)));
            return listTeamDTO;
        } catch (TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets team by id.
     *
     * @param id the id
     * @return the team by id
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public TeamDTO getTeamById(@PathVariable("id") Long id) throws PresentationException {
        try {
            return boToDTOConverter.teamBOToDTO(teamService.getTeamById(id));
        } catch (TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Gets teams by modality.
     *
     * @param modality the modality
     * @return the teams by modality
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/modality/{modality}")
    public List<TeamDTO> getTeamsByModality(@PathVariable("modality") String modality) throws PresentationException {
        List<TeamDTO> listTeamDTO = new ArrayList<TeamDTO>();
        try {
            ModalityBO found = modalityService.getModalityByName(modality);
            teamService.getTeamsByModality(found)
                    .forEach((TeamBO team) -> listTeamDTO.add(boToDTOConverter.teamBOToDTO(team)));
            return listTeamDTO;
        } catch (ModalityNotFoundException | TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets team by name.
     *
     * @param name the name
     * @return the team by name
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name/{name}")
    public List<TeamDTO> getTeamByName(@PathVariable("name") String name) throws PresentationException {
        List<TeamDTO> listTeamDTO = new ArrayList<TeamDTO>();
        try {
            teamService.getTeamsByName(name)
                    .forEach((TeamBO team) -> listTeamDTO.add(boToDTOConverter.teamBOToDTO(team)));
            return listTeamDTO;
        } catch (TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Registerteam team dto.
     *
     * @param team the team
     * @return the team dto
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public TeamDTO registerteam(@RequestBody @Valid RegisterTeamDTO team) throws PresentationException {
        try {
            Long captain = authenticationService.getUserAuthenticated();
            return boToDTOConverter
                    .teamBOToDTO(teamService.registerTeam(captain, dtoToBOConverter.RegisterTeamDTOToBO(team)));
        } catch (DuplicatedCaptainException | DuplicatedNameAndModalityException e) {
            throw new PresentationException(e.getMessage());
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Add player team dto.
     *
     * @param team the team
     * @return the team dto
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/addPlayer/{team}/")
    public TeamDTO addPlayer(@PathVariable Long team) {
        try {
            Long user = authenticationService.getUserAuthenticated();
            return boToDTOConverter.teamBOToDTO(teamService.addPlayer(user, team));
        } catch (UserNotFoundException | TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Delete team.
     *
     * @param id the id
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable Long id) {
        try {
            teamService.deleteTeam(id);
        } catch (TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Update team.
     *
     * @param team the team
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/update")
    public void updateTeam(@RequestBody @Valid TeamDTO team) {
        try {
            teamService.updateTeam(dtoToBOConverter.teamDTOToBO(team));
        } catch (TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }
}
