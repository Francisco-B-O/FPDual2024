package com.Dual2024.ProjectCompetition.Presentation.Controller;

import com.Dual2024.ProjectCompetition.Business.BusinessException.*;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.Service.ModalityService;
import com.Dual2024.ProjectCompetition.Business.Service.Security.AuthenticationService;
import com.Dual2024.ProjectCompetition.Business.Service.TeamService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RegisterTeamDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.TeamDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("team")
@RestController
public class TeamController {
    @Autowired
    BOToDTOConverter boToDTOConverter;
    @Autowired
    DTOToBOConverter dtoToBOConverter;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ModalityService modalityService;
    @Autowired
    AuthenticationService authenticationService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<TeamDTO> getAllteams() throws PresentationException {
        List<TeamDTO> listTeamDTO = new ArrayList<TeamDTO>();
        try {
            teamService.getAllteams().forEach((TeamBO team) -> listTeamDTO.add(boToDTOConverter.teamBOToDTO(team)));
            return listTeamDTO;
        } catch (TeamNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

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
