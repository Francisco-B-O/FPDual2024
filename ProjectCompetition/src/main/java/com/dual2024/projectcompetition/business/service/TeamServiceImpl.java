package com.dual2024.projectcompetition.business.service;

import com.dual2024.projectcompetition.dataaccess.dao.TeamDAO;
import com.dual2024.projectcompetition.dataaccess.dao.UserDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Team;
import com.dual2024.projectcompetition.business.businessexception.*;
import com.dual2024.projectcompetition.business.businessobject.ModalityBO;
import com.dual2024.projectcompetition.business.businessobject.TeamBO;
import com.dual2024.projectcompetition.business.businessobject.UserBOAux;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TeamService interface.
 *
 * @author Francisco Balonero Olivera
 * @see TeamService
 * @see UserDAO
 * @see TeamDAO
 * @see BOToModelConverter
 * @see ModelToBOConverter
 */
@Slf4j
@Service
public class TeamServiceImpl implements TeamService {
    /**
     * The Team DAO.
     */
    @Autowired
    TeamDAO teamDAO;

    /**
     * The User DAO.
     */
    @Autowired
    UserDAO userDAO;

    /**
     * The BO to Model converter.
     */
    @Autowired
    BOToModelConverter boToModelConverter;

    /**
     * The Model to BO converter.
     */
    @Autowired
    ModelToBOConverter modelToBOConverter;

    @Override
    @Transactional
    public TeamBO registerTeam(Long captainId, TeamBO teamBO) throws BusinessException {
        try {
            UserBOAux captain;
            if (captainId == null) {
                log.error("Captain not found");
                throw new UserNotFoundException("Captain not found");
            }
            try {
                captain = modelToBOConverter.userModelToBOAux(userDAO.findById(captainId));
            } catch (DataException e) {
                log.error("Captain not found", e);
                throw new UserNotFoundException("Captain not found", e);
            }

            try {
                teamDAO.findByCaptain(boToModelConverter.userBOAuxToModel(captain));
                log.error("This user is already captain of another team");
                throw new DuplicatedCaptainException("This user is already captain of another team");
            } catch (DataException ignored) {
            }

            List<TeamBO> listTeamsBO = new ArrayList<>();
            try {
                teamDAO.findByModality(boToModelConverter.modalityBOToModel(teamBO.getModality()))
                        .forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
                for (TeamBO foundTeam : listTeamsBO) {
                    if (foundTeam.getName().equals(teamBO.getName())) {
                        log.error("The combination of name+modality already exists");
                        throw new DuplicatedNameAndModalityException("The combination of name+modality already exists");
                    }
                }
            } catch (DataException ignored) {
            }

            List<UserBOAux> userCaptain = new ArrayList<>();
            userCaptain.add(captain);
            teamBO.setUsers(userCaptain);

            return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(teamBO)));
        } catch (DataException e) {
            log.error("Error registering team", e);
            throw new BusinessException("Error registering team", e);
        }
    }

    @Override
    public TeamBO getTeamById(Long id) throws BusinessException {
        try {
            return modelToBOConverter.teamModelToBO(teamDAO.findById(id));
        } catch (EntityNotFoundException e) {
            log.error("Team not found", e);
            throw new TeamNotFoundException("Team not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find team", e);
            throw new BusinessException("Error when trying to find team", e);
        }
    }

    @Override
    public List<TeamBO> getAllTeams() throws BusinessException {
        List<TeamBO> listTeamsBO = new ArrayList<>();
        try {
            teamDAO.findAll().forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
            return listTeamsBO;
        } catch (EntityNotFoundException e) {
            log.error("Teams not found", e);
            throw new TeamNotFoundException("Teams not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find teams", e);
            throw new BusinessException("Error when trying to find teams", e);
        }
    }

    @Override
    @Transactional
    public void deleteTeam(Long id) throws BusinessException {
        try {
            TeamBO teamBO = modelToBOConverter.teamModelToBO(teamDAO.findById(id));
            if (teamBO.isInActiveTournament()) {
                log.error("This team is in an active tournament");
                throw new TeamInActiveTournamentException("This team is in an active tournament");
            } else {
                teamDAO.delete(id);
            }
        } catch (EntityNotFoundException e) {
            log.error("Team not found", e);
            throw new TeamNotFoundException("Team not found", e);
        } catch (DataException e) {
            log.error("Team not deleted", e);
            throw new BusinessException("Team not deleted", e);
        }
    }

    @Override
    public List<TeamBO> getTeamsByName(String name) throws BusinessException {
        List<TeamBO> listTeamsBO = new ArrayList<>();
        try {
            teamDAO.findByName(name).forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
            return listTeamsBO;
        } catch (EntityNotFoundException e) {
            log.error("Teams not found", e);
            throw new TeamNotFoundException("Teams not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find teams", e);
            throw new BusinessException("Error when trying to find teams", e);
        }
    }

    @Override
    public List<TeamBO> getTeamsByModality(ModalityBO modalityBO) throws BusinessException {
        List<TeamBO> listTeamsBO = new ArrayList<>();
        try {
            teamDAO.findByModality(boToModelConverter.modalityBOToModel(modalityBO))
                    .forEach((Team team) -> listTeamsBO.add(modelToBOConverter.teamModelToBO(team)));
            return listTeamsBO;
        } catch (EntityNotFoundException e) {
            log.error("Teams not found", e);
            throw new TeamNotFoundException("Teams not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find teams", e);
            throw new BusinessException("Error when trying to find teams", e);
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
        } catch (EntityNotFoundException e) {
            log.error("This team not exists", e);
            throw new TeamNotFoundException("This team not exists", e);
        } catch (DataException e) {
            log.error("Team could not be updated", e);
            throw new BusinessException("Team could not be updated", e);
        }
        try {
            return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(team)));
        } catch (DataException e) {
            log.error("Team could not be updated", e);
            throw new BusinessException("Team could not be updated", e);
        }
    }

    @Override
    @Transactional
    public TeamBO addPlayer(Long userId, Long teamId) throws BusinessException {
        UserBOAux user;
        try {
            user = modelToBOConverter.userModelToBOAux(userDAO.findById(userId));
        } catch (EntityNotFoundException e) {
            log.error("This user not exists", e);
            throw new UserNotFoundException("This user not exists", e);
        } catch (DataException e) {
            log.error("Player could not be added", e);
            throw new BusinessException("Player could not be added", e);
        }
        TeamBO team;
        try {
            team = modelToBOConverter.teamModelToBO(teamDAO.findById(teamId));
            List<UserBOAux> players = new ArrayList<>();
            if (team.getUsers() == null) {
                players.add(user);
            } else if (team.getUsers().size() >= team.getModality().getNumberPlayers()) {
                log.error("Full team");
                throw new FullTeamException("Full team");
            } else if (team.getUsers().contains(user)) {
                log.error("This user is already on the team");
                throw new BusinessException("This user is already on the team");
            } else {
                players = team.getUsers();
                players.add(user);
            }
            team.setUsers(players);
        } catch (EntityNotFoundException e) {
            log.error("This team not exists", e);
            throw new TeamNotFoundException("This team not exists", e);
        } catch (DataException e) {
            log.error("Player could not be added", e);
            throw new BusinessException("Player could not be added", e);
        }
        try {
            return modelToBOConverter.teamModelToBO(teamDAO.save(boToModelConverter.teamBOToModel(team)));
        } catch (DataException e) {
            log.error("Player could not be added", e);
            throw new BusinessException("Player could not be added", e);
        }
    }

}
