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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Tournament controller.
 *
 * @author Franciosco Balonero Olivera
 */
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
     * @return the all tournaments
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
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
     * @param id the id
     * @return the tournament by id
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public TournamentDTO getTournamentById(@PathVariable("id") Long id) throws PresentationException {
        try {
            return boToDTOConverter.tournamentBOToDTO(tournamentService.getTournamentById(id));
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Gets tournament by state.
     *
     * @param state the state
     * @return the tournament by state
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/state/{state}")
    public List<TournamentDTO> getTournamentByState(@PathVariable("state") TournamentState state)
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
     * @param modality the modality
     * @return the tournaments by modality
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/modality/{modality}")
    public List<TournamentDTO> getTournamentsByModality(@PathVariable("modality") String modality)
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
     * @param format the format
     * @return the tournaments by format
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/format/{format}")
    public List<TournamentDTO> getTournamentsByFormat(@PathVariable("format") String format)
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
     * Gets tournament by name.
     *
     * @param name the name
     * @return the tournament by name
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name/{name}")
    public List<TournamentDTO> getTournamentByName(@PathVariable("name") String name) throws PresentationException {
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
     * Gets tournament by size.
     *
     * @param size the size
     * @return the tournament by size
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/size/{size}")
    public List<TournamentDTO> getTournamentBySize(@PathVariable("size") int size) throws PresentationException {
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
     * Gets tournament by start date.
     *
     * @param start the start
     * @return the tournament by start date
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/start/{start}")
    public List<TournamentDTO> getTournamentByStartDate(@PathVariable("start") LocalDateTime start)
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
     * Gets tournament by end date.
     *
     * @param end the end
     * @return the tournament by end date
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @GetMapping("/end/{end}")
    public List<TournamentDTO> getTournamentByEndDate(@PathVariable("end") LocalDateTime end)
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
     * Register tournament tournament dto.
     *
     * @param tournament the tournament
     * @return the tournament dto
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
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
     * @param id the id
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteTournament(@PathVariable long id) {
        try {
            tournamentService.deleteTournament(id);
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Add team.
     *
     * @param team       the team
     * @param tournament the tournament
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/addTeam/{tournament}/{team}")
    public void addTeam(@PathVariable long team, @PathVariable long tournament) {
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
     * @param tournament the tournament
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/update")
    public void updateTournament(@RequestBody @Valid TournamentDTO tournament) {
        try {
            tournamentService.updateTournament(dtoToBOConverter.tournamentDTOToBO(tournament));
        } catch (TournamentNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

}
