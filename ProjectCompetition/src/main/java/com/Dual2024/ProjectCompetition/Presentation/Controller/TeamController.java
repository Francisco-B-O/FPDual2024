package com.Dual2024.ProjectCompetition.Presentation.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedCaptainException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameAndModalityException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.FullTeamException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.ModalityNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.TeamInActiveTournamentException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.TeamNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.Service.ModalityService;
import com.Dual2024.ProjectCompetition.Business.Service.TeamService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RegisterTeamDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.TeamDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;

import jakarta.validation.Valid;

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

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/modality/{modality}")
	public List<TeamDTO> getTeamsByModality(@PathVariable("modality") String modality) throws PresentationException {
		List<TeamDTO> listTeamDTO = new ArrayList<TeamDTO>();
		try {
			ModalityBO found = modalityService.getModalityByName(modality);
			teamService.getTeamsByModality(found)
					.forEach((TeamBO team) -> listTeamDTO.add(boToDTOConverter.teamBOToDTO(team)));
			return listTeamDTO;
		} catch (ModalityNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (TeamNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

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

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/register")
	public TeamDTO registerteam(@RequestBody @Valid RegisterTeamDTO team) throws PresentationException {
		Long captain = null;
		try {
			return boToDTOConverter
					.teamBOToDTO(teamService.registerTeam(captain, dtoToBOConverter.RegisterTeamDTOToBO(team)));
		} catch (DuplicatedCaptainException e) {
			throw new PresentationException(e.getMessage());
		} catch (DuplicatedNameAndModalityException e) {
			throw new PresentationException(e.getMessage());
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/addPlayer")
	public void addPlayer(@RequestParam Long user, Long team) {
		try {
			teamService.addPlayer(user, team);
		} catch (UserNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (FullTeamException e) {
			throw new PresentationException(e.getMessage(), e);
		} catch (TeamNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@DeleteMapping("/delete")
	public void deleteTeam(@RequestParam Long id) {
		try {
			teamService.deleteTeam(id);
		} catch (TeamInActiveTournamentException e) {
			throw new PresentationException(e.getMessage(), e);
		} catch (TeamNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

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
