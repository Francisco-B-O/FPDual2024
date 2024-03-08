package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.*;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TeamDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
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
    @Transactional
    public TeamBO registerTeam(Long captainId, TeamBO teamBO) throws BusinessException {
        UserBOAux captain;
        if (captainId == null) {
            throw new UserNotFoundException("Captain not found");
        }
        try {
            captain = modelToBOConverter.userModelToBOAux(userDAO.findById(captainId));

        } catch (DataException e) {
            throw new UserNotFoundException("Captain not found", e);
        }
        try {
            teamDAO.findByCaptain(boToModelConverter.userBOAuxToModel(captain));
            throw new DuplicatedCaptainException("This user is already captain of another team");
        } catch (DataException ignored) {
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
        } catch (DataException ignored) {
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
        } catch (NotFoundException e) {
            throw new TeamNotFoundException("Team not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find team", null);
        }
    }

    @Override
    public List<TeamBO> getAllteams() throws BusinessException {
        List<TeamBO> listTeamsBO = new ArrayList<TeamBO>();
        try {
            teamDAO.findAll().forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
            return listTeamsBO;
        } catch (NotFoundException e) {
            throw new TeamNotFoundException("Teams not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find teams", null);
        }
    }

    @Override
    @Transactional
    public void deleteTeam(Long id) throws BusinessException {

        try {
            TeamBO teamBO = modelToBOConverter.teamModelToBO(teamDAO.findById(id));
            if (teamBO.isInActiveTournament()) {
                throw new TeamInActiveTournamentException("This team is in an active tournament");
            } else {
                teamDAO.delete(id);
            }
        } catch (NotFoundException e) {
            throw new TeamNotFoundException("Team not found", e);
        } catch (DataException e) {
            throw new BusinessException("Team not deleted", null);
        }

    }

    @Override
    public List<TeamBO> getTeamsByName(String name) throws BusinessException {
        List<TeamBO> listTeamsBO = new ArrayList<TeamBO>();
        try {
            teamDAO.findByName(name).forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
            return listTeamsBO;
        } catch (NotFoundException e) {
            throw new TeamNotFoundException("Teams not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find teams", null);
        }
    }

    @Override
    public List<TeamBO> getTeamsByModality(ModalityBO modalityBO) throws BusinessException {
        List<TeamBO> listTeamsBO = new ArrayList<TeamBO>();
        try {
            teamDAO.findByModality(boToModelConverter.modalityBOToModel(modalityBO))
                    .forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
            return listTeamsBO;
        } catch (NotFoundException e) {
            throw new TeamNotFoundException("Teams not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find teams", null);
        }
    }

    @Override
    @Transactional
    public TeamBO updateTeam(TeamBO teamBO) throws BusinessException {
        TeamBO team;
        try {
            team = modelToBOConverter.teamModelToBO(teamDAO.findById(teamBO.getId()));
            team.setLogo(teamBO.getLogo());
            team.setName(teamBO.getName());
            team.setUsers(teamBO.getUsers());
            team.setTournaments(team.getTournaments());
        } catch (NotFoundException e) {
            throw new TeamNotFoundException("This team not exists", e);
        } catch (DataException e) {
            throw new BusinessException("Team could not be updated", e);
        }
        try {
            return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(team)));
        } catch (DataException e) {
            throw new BusinessException("Team could not be updated", e);
        }
    }

    @Override
    @Transactional
    public TeamBO addPlayer(Long userId, Long teamId) throws BusinessException {
        UserBOAux user;
        try {
            user = modelToBOConverter.userModelToBOAux(userDAO.findById(userId));
        } catch (NotFoundException e) {
            throw new UserNotFoundException("This user not exists", e);
        } catch (DataException e) {
            throw new BusinessException("Player could not be added", e);
        }
        TeamBO team;
        try {
            team = modelToBOConverter.teamModelToBO(teamDAO.findById(teamId));
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
        } catch (NotFoundException e) {
            throw new TeamNotFoundException("This team not exists", e);
        } catch (DataException e) {
            throw new BusinessException("Player could not be added", e);
        }
        try {
            return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(team)));
        } catch (DataException e) {
            throw new BusinessException("Player could not be added", e);
        }
    }

}
