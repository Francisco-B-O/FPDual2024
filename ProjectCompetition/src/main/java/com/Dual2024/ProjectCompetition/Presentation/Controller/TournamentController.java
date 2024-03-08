package com.Dual2024.ProjectCompetition.Presentation.Controller;

import com.Dual2024.ProjectCompetition.Business.BusinessException.*;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.Business.Service.FormatService;
import com.Dual2024.ProjectCompetition.Business.Service.ModalityService;
import com.Dual2024.ProjectCompetition.Business.Service.TournamentService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RegisterTournamentDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.TournamentDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("tournament")
@RestController
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private ModalityService modalityService;
    @Autowired
    private FormatService formatService;
    @Autowired
    BOToDTOConverter boToDTOConverter;
    @Autowired
    DTOToBOConverter dtoToBOConverter;

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
