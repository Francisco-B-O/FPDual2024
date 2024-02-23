package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedCaptainException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameAndModalityException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.FullTeamException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.TeamNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserInActiveTournamentException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TeamDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
	@Autowired
	TeamDAO teamDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	BOToModelConverter boToModelConverter;

	@Autowired
	ModelToBOConverter modelToBOConverter;

	@Override
	public TeamBO registerTeam(UserBOAux captain, TeamBO teamBO) throws BusinessException {

		try {
			teamDAO.findByCaptain(boToModelConverter.userBOAuxToModel(captain));
			throw new DuplicatedCaptainException("This user is already captain of another team");
		} catch (DataException e) {
		}

		List<TeamBO> listTeamsBO = new ArrayList<TeamBO>();
		try {
			teamDAO.findByModality(boToModelConverter.modalityBOToModel(teamBO.getModality()))
					.forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
			for (TeamBO foundTeam : listTeamsBO) {
				if (foundTeam.getName().equals(teamBO.getName())) {
					throw new DuplicatedNameAndModalityException("The combination of name+modality already exists");
				}
			}
		} catch (DataException e) {
		}
		try {
			List<UserBOAux> userCaptain = new ArrayList<UserBOAux>();
			userCaptain.add(captain);
			teamBO.setUsers(userCaptain);
			return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(teamBO)));
		} catch (DataException e) {
			throw new BusinessException("Error registering team", e);
		}

	}

	@Override
	public TeamBO getTeamById(Long id) throws BusinessException {
		try {
			return modelToBOConverter.teamModelToBO(teamDAO.findById(id));
		} catch (DataException e) {
			throw new TeamNotFoundException("Team not found", e);
		}
	}

	@Override
	public List<TeamBO> getAllteams() throws BusinessException {
		List<TeamBO> listTeamsBO = new ArrayList<TeamBO>();
		try {
			teamDAO.findAll().forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
			return listTeamsBO;
		} catch (DataException e) {
			throw new TeamNotFoundException("Teams not found", e);
		}
	}

	@Override
	public void deleteTeam(Long id) throws BusinessException {
		TeamBO teamBO;
		try {
			teamBO = modelToBOConverter.teamModelToBO(teamDAO.findById(id));
		} catch (DataException e) {
			throw new TeamNotFoundException("Team not found", e);
		}
		if (teamBO.isInActiveTournament()) {
			throw new UserInActiveTournamentException("This team is in an active tournament");
		} else {
			try {
				teamDAO.delete(boToModelConverter.teamBOToModel(teamBO));
			} catch (DataException e) {
				throw new BusinessException("Team not deleted", e);
			}
		}

	}

	@Override
	public List<TeamBO> getTeamsByName(String name) throws BusinessException {
		List<TeamBO> listTeamsBO = new ArrayList<TeamBO>();
		try {
			teamDAO.findByName(name).forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
			return listTeamsBO;
		} catch (DataException e) {
			throw new TeamNotFoundException("Teams not found", e);
		}
	}

	@Override
	public List<TeamBO> getTeamsByModality(ModalityBO modalityBO) throws BusinessException {
		List<TeamBO> listTeamsBO = new ArrayList<TeamBO>();
		try {
			teamDAO.findByModality(boToModelConverter.modalityBOToModel(modalityBO))
					.forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
			return listTeamsBO;
		} catch (DataException e) {
			throw new TeamNotFoundException("Teams not found", e);
		}
	}

	@Override
	public TeamBO updateTeam(TeamBO teamBO) throws BusinessException {
		TeamBO team = null;
		try {
			team = modelToBOConverter.teamModelToBO(teamDAO.findById(teamBO.getId()));
			team.setLogo(teamBO.getLogo());
			team.setName(teamBO.getName());
			team.setUsers(teamBO.getUsers());
			team.setTournaments(team.getTournaments());
		} catch (DataException e) {
			throw new TeamNotFoundException("This team not exists", e);
		}
		try {
			return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(team)));
		} catch (DataException e) {
			throw new BusinessException("Team could not be updated", e);
		}
	}

	@Override
	public TeamBO addPlayer(Long id, TeamBO teamBO) throws BusinessException {
		UserBOAux user = null;

		try {
			user = modelToBOConverter.userModelToBOAux(userDAO.findById(id));
		} catch (DataException e) {
			throw new UserNotFoundException("This user not exists", e);
		}
		TeamBO team = null;
		try {
			team = modelToBOConverter.teamModelToBO(teamDAO.findById(teamBO.getId()));
			List<UserBOAux> players = new ArrayList<UserBOAux>();
			if (team.getUsers() == null) {
				players.add(user);
			} else if (team.getUsers().size() >= team.getModality().getNumberPlayers()) {
				throw new FullTeamException("Full team");
			} else if (team.getUsers().contains(user)) {
				throw new BusinessException("This user is already on the team ");
			} else {
				players = team.getUsers();
				players.add(user);
			}
			team.setUsers(players);
		} catch (DataException e) {
			throw new TeamNotFoundException("This team not exists", e);
		}
		try {
			return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(team)));
		} catch (DataException e) {
			throw new BusinessException("Player could not be added", e);
		}
	}

}
