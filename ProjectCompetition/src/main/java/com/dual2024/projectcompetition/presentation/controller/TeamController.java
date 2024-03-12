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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing teams in the system.
 *
 * <p>This class defines RESTful endpoints for various team-related operations.It handles
 * requests related to retrieving all teams, retrieving a teams by ID or name, adding, updating,adding players,
 * and deleting teams. The endpoints are secured, and only authorized users with specific roles
 * can access them.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see TeamService
 * @see ModalityService
 * @see BOToDTOConverter
 * @see DTOToBOConverter
 */
@RequestMapping("team")
@RestController
@Tag(name = "Team", description = "Operations related to teams management")
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
     * Retrieves all teams in the system.
     *
     * @return List of all teams
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get all teams")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<TeamDTO> getAllTeams() throws PresentationException {
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
     * Retrieves a team by its unique identifier.
     *
     * @param id The identifier of the team
     * @return TeamDTO representing the team with the specified ID
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get team by ID")
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
     * Retrieves teams by the specified modality.
     *
     * @param modality The name of the modality
     * @return List of teams within the specified modality
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get teams by modality")
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
     * Retrieves teams by the specified name.
     *
     * @param name The name of the team
     * @return List of teams with the specified name
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get team by name")
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
     * Registers a new team.
     *
     * @param team The team information to register
     * @return TeamDTO representing the newly registered team
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Register a new team")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public TeamDTO registerTeam(@RequestBody @Valid RegisterTeamDTO team) throws PresentationException {
        try {
            Long captain = authenticationService.getUserAuthenticated();
            return boToDTOConverter.teamBOToDTO(teamService.registerTeam(captain, dtoToBOConverter.RegisterTeamDTOToBO(team)));
        } catch (DuplicatedCaptainException | DuplicatedNameAndModalityException e) {
            throw new PresentationException(e.getMessage());
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Adds a player to the specified team.
     *
     * @param team   The ID of the team to which the player will be added
     * @param player The ID of the player
     * @return TeamDTO representing the updated team
     */
    @Operation(summary = "Add player to the team")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/addPlayer/{team}/{player}")
    public TeamDTO addPlayer(@PathVariable Long team, @PathVariable Long player) {
        try {
            return boToDTOConverter.teamBOToDTO(teamService.addPlayer(player, team));
        } catch (UserNotFoundException | TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Deletes the specified team.
     *
     * @param id The ID of the team to be deleted
     */
    @Operation(summary = "Delete team")
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
     * Updates information for the specified team.
     *
     * @param team The updated information for the team
     */
    @Operation(summary = "Update team")
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