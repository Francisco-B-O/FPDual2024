package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.businessexception.*;
import com.dual2024.projectcompetition.business.businessobject.FormatBO;
import com.dual2024.projectcompetition.business.businessobject.ModalityBO;
import com.dual2024.projectcompetition.business.businessobject.TournamentBO;
import com.dual2024.projectcompetition.business.service.FormatService;
import com.dual2024.projectcompetition.business.service.ModalityService;
import com.dual2024.projectcompetition.business.service.TournamentService;
import com.dual2024.projectcompetition.presentation.dto.RegisterTournamentDTO;
import com.dual2024.projectcompetition.presentation.dto.TournamentDTO;
import com.dual2024.projectcompetition.presentation.dto.converters.BOToDTOConverter;
import com.dual2024.projectcompetition.presentation.dto.converters.DTOToBOConverter;
import com.dual2024.projectcompetition.presentation.exception.NotFoundException;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
import com.dual2024.projectcompetition.utils.TournamentState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing tournaments.
 *
 * <p>This class defines RESTful endpoints for various tournament-related operations.
 * It handles requests related to retrieving all tournaments, retrieving a tournament by ID,
 * retrieving tournaments by state, modality, format, name, size, start date, or end date,
 * registering a new tournament, deleting a tournament, adding a team to a tournament,
 * and updating tournament information. The endpoints are secured, and only authorized users
 * with specific roles can access them.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see TournamentService
 * @see ModalityService
 * @see FormatService
 * @see BOToDTOConverter
 * @see DTOToBOConverter
 */
@Tag(name = "Tournament", description = "Operations related to tournaments management")
@RequestMapping("tournament")
@RestController
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private ModalityService modalityService;
    @Autowired
    private FormatService formatService;
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

    /**
     * Gets all tournaments.
     *
     * @return {@link List} the list of all tournaments
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get all tournaments")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<TournamentDTO> getAllTournaments() throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            tournamentService.getAllTournaments().forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournament by id.
     *
     * @param id {@link Long} the id of the tournament
     * @return {@link TournamentDTO} the tournament with the specified id
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournament by ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public TournamentDTO getTournamentById(@PathVariable("id") @Parameter(description = "ID of the tournament") Long id) throws PresentationException {
        try {
            return boToDTOConverter.tournamentBOToDTO(tournamentService.getTournamentById(id));
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournaments by state.
     *
     * @param state {@link TournamentState} the state of the tournaments to retrieve
     * @return {@link List} the list of tournaments with the specified state
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournaments by state")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/state/{state}")
    public List<TournamentDTO> getTournamentByState(@PathVariable("state") @Parameter(description = "State of the tournament") TournamentState state)
            throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            tournamentService.getTournamentsByState(state).forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournaments by modality.
     *
     * @param modality {@link String} the modality of the tournaments to retrieve
     * @return {@link List} the list of tournaments with the specified modality
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournaments by modality")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/modality/{modality}")
    public List<TournamentDTO> getTournamentsByModality(@PathVariable("modality") @Parameter(description = "Modality name of the tournament") String modality)
            throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            ModalityBO found = modalityService.getModalityByName(modality);
            tournamentService.getTournamentsByModality(found).forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (ModalityNotFoundException | TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournaments by format.
     *
     * @param format {@link String} the format of the tournaments to retrieve
     * @return {@link List} the list of tournaments with the specified format
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournaments by format")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/format/{format}")
    public List<TournamentDTO> getTournamentsByFormat(@PathVariable("format") @Parameter(description = "Format name of the tournament") String format)
            throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            FormatBO found = formatService.getFormatByName(format);
            tournamentService.getTournamentsByFormat(found).forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (FormatNotFoundException | TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournaments by name.
     *
     * @param name {@link String} the name of the tournaments to retrieve
     * @return {@link List} the list of tournaments with the specified name
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournaments by name")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name/{name}")
    public List<TournamentDTO> getTournamentByName(@PathVariable("name") @Parameter(description = "Name of the tournament") String name) throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            tournamentService.getTournamentsByName(name).forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournaments by size.
     *
     * @param size {@link Integer} the size of the tournaments to retrieve
     * @return {@link List} the list of tournaments with the specified size
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournaments by size")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/size/{size}")
    public List<TournamentDTO> getTournamentBySize(@PathVariable("size") @Parameter(description = "Size of the tournament") int size) throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            tournamentService.getTournamentsBySize(size).forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournaments by start date.
     *
     * @param start {@link LocalDateTime} the start date of the tournaments to retrieve
     * @return {@link List} the list of tournaments starting from the specified date
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournaments by start date")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/start/{start}")
    public List<TournamentDTO> getTournamentByStartDate(@PathVariable("start") @Parameter(description = "Start date of the tournament") LocalDateTime start)
            throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            tournamentService.getTournamentsByStartDate(start).forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets tournaments by end date.
     *
     * @param end {@link LocalDateTime} the end date of the tournaments to retrieve
     * @return {@link List} the list of tournaments ending on the specified date
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Get tournaments by end date")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_PLAYER')")
    @GetMapping("/end/{end}")
    public List<TournamentDTO> getTournamentByEndDate(@PathVariable("end") @Parameter(description = "End date of the tournament") LocalDateTime end)
            throws PresentationException {
        List<TournamentDTO> listTournamentDTO = new ArrayList<TournamentDTO>();
        try {
            tournamentService.getTournamentsByEndDate(end).forEach(
                    (TournamentBO tournament) -> listTournamentDTO.add(boToDTOConverter.tournamentBOToDTO(tournament)));
            return listTournamentDTO;
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Register tournament.
     *
     * @param tournament {@link RegisterTournamentDTO} the tournament to register
     * @return {@link TournamentDTO} the registered tournament
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Register tournament")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public TournamentDTO registerTournament(@RequestBody @Valid RegisterTournamentDTO tournament)
            throws PresentationException {
        try {
            return boToDTOConverter.tournamentBOToDTO(
                    tournamentService.registerTournament(dtoToBOConverter.RegisterTournamentDTOToBO(tournament)));
        } catch (DuplicatedNameAndModalityException | InvalidDateException e) {
            throw new PresentationException(e.getMessage());
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Delete tournament.
     *
     * @param id {@link Long} the id of the tournament to delete
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Delete tournament")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteTournament(@PathVariable @Parameter(description = "ID of the tournament to be deleted") long id) throws PresentationException {
        try {
            tournamentService.deleteTournament(id);
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Add team to tournament.
     *
     * @param team       {@link Long} the id of the team to add
     * @param tournament {@link Long} the id of the tournament to add the team to
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Add team to tournament")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/addTeam/{tournament}/{team}")
    public void addTeam(@PathVariable @Parameter(description = "The ID of the team") long team, @PathVariable @Parameter(description = "The ID of the tournament") long tournament) throws PresentationException {
        try {
            tournamentService.addTeam(team, tournament);
        } catch (TeamNotFoundException | TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Update tournament.
     *
     * @param tournament {@link TournamentDTO} the tournament to update
     * @throws PresentationException if there is an issue with the presentation layer
     */
    @Operation(summary = "Update tournament")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/update")
    public void updateTournament(@RequestBody @Valid TournamentDTO tournament) throws PresentationException {
        try {
            tournamentService.updateTournament(dtoToBOConverter.tournamentDTOToBO(tournament));
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }
}