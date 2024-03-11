package com.dual2024.projectcompetition.business.service;

import com.dual2024.projectcompetition.dataaccess.dao.TeamDAO;
import com.dual2024.projectcompetition.dataaccess.dao.TournamentDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Tournament;
import com.dual2024.projectcompetition.utils.TournamentState;
import com.dual2024.projectcompetition.business.businessexception.*;
import com.dual2024.projectcompetition.business.businessobject.*;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TournamentService interface.
 *
 * @author Francisco Balonero Olivera
 * @see TournamentService
 * @see TeamDAO
 * @see TournamentDAO
 * @see BOToModelConverter
 * @see ModelToBOConverter
 */
@Slf4j
@Service
public class TournamentServiceImpl implements TournamentService {
    /**
     * The Tournament DAO.
     */
    @Autowired
    TournamentDAO tournamentDAO;

    /**
     * The Team DAO.
     */
    @Autowired
    TeamDAO teamDAO;

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
    public TournamentBO registerTournament(TournamentBO tournamentBO) throws BusinessException {
        try {
            List<TournamentBO> listTournamentsBO = new ArrayList<>();
            tournamentDAO.findByModality(boToModelConverter.modalityBOToModel(tournamentBO.getModality()))
                    .forEach((Tournament tournament) -> listTournamentsBO
                            .add(modelToBOConverter.tournamentModelToBO(tournament)));
            if (listTournamentsBO.stream().anyMatch(c -> c.getName().equals(tournamentBO.getName()))) {
                log.error("The combination of name+modality already exists");
                throw new DuplicatedNameAndModalityException("The combination of name+modality already exists");
            }

        } catch (DataException ignored) {
        }
        if (tournamentBO.getStartDate().isEqual(tournamentBO.getEndDate())
                || tournamentBO.getStartDate().isAfter(tournamentBO.getEndDate())) {
            log.error("Invalid dates");
            throw new InvalidDateException("Invalid dates");
        } else {
            try {
                return modelToBOConverter
                        .tournamentModelToBO(tournamentDAO.save(boToModelConverter.tournamentBOToModel(tournamentBO)));
            } catch (DataException e) {
                log.error("Error registering tournament", e);
                throw new BusinessException("Error registering tournament", e);
            }
        }
    }

    @Override
    public TournamentBO getTournamentById(Long id) throws BusinessException {
        try {
            return modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(id));
        } catch (EntityNotFoundException e) {
            log.error("Tournament not found", e);
            throw new TournamentNotFoundException("Tournament not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournament", e);
            throw new BusinessException("Error when trying to find tournament", e);
        }
    }

    @Override
    public List<TournamentBO> getAllTournaments() throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findAll().forEach((Tournament tournament) -> listTournamentBO
                    .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    @Transactional
    public void deleteTournament(Long id) throws BusinessException {
        try {
            TournamentBO tournamentBO = modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(id));
            if (tournamentBO.getState().equals(TournamentState.EN_JUEGO)) {
                log.error("This tournament is active");
                throw new ActiveTournamentException("This tournament is active");
            } else {
                tournamentDAO.delete(id);
            }
        } catch (EntityNotFoundException e) {
            log.error("Tournament not found", e);
            throw new TournamentNotFoundException("Tournament not found", e);
        } catch (DataException e) {
            log.error("Tournament not deleted", e);
            throw new BusinessException("Tournament not deleted", e);
        }
    }

    @Override
    public List<TournamentBO> getTournamentsByName(String name) throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findByName(name).forEach((Tournament tournament) -> listTournamentBO
                    .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    public List<TournamentBO> getTournamentsByFormat(FormatBO formatBO) throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findByFormat(boToModelConverter.formatBOToModel(formatBO))
                    .forEach((Tournament tournament) -> listTournamentBO
                            .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    public List<TournamentBO> getTournamentsBySize(int size) throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findBySize(size).forEach((Tournament tournament) -> listTournamentBO
                    .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    public List<TournamentBO> getTournamentsByStartDate(LocalDateTime startDate) throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findByStartDate(startDate).forEach((Tournament tournament) -> listTournamentBO
                    .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    public List<TournamentBO> getTournamentsByEndDate(LocalDateTime endDate) throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findByEndDate(endDate).forEach((Tournament tournament) -> listTournamentBO
                    .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    public List<TournamentBO> getTournamentsByState(TournamentState state) throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findByState(state).forEach((Tournament tournament) -> listTournamentBO
                    .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    public List<TournamentBO> getTournamentsByModality(ModalityBO modalityBO) throws BusinessException {
        List<TournamentBO> listTournamentBO = new ArrayList<>();
        try {
            tournamentDAO.findByModality(boToModelConverter.modalityBOToModel(modalityBO))
                    .forEach((Tournament tournament) -> listTournamentBO
                            .add(modelToBOConverter.tournamentModelToBO(tournament)));
            return listTournamentBO;
        } catch (EntityNotFoundException e) {
            log.error("Tournaments not found", e);
            throw new TournamentNotFoundException("Tournaments not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find tournaments", e);
            throw new BusinessException("Error when trying to find tournaments", e);
        }
    }

    @Override
    @Transactional
    public TournamentBO updateTournament(TournamentBO tournamentBO) throws BusinessException {
        try {
            modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(tournamentBO.getId()));
            return modelToBOConverter
                    .tournamentModelToBO(tournamentDAO.save(boToModelConverter.tournamentBOToModel(tournamentBO)));
        } catch (EntityNotFoundException e) {
            log.error("This tournament not exists", e);
            throw new TournamentNotFoundException("This tournament not exists", e);
        } catch (DataException e) {
            log.error("Tournament could not be updated", e);
            throw new BusinessException("Tournament could not be updated", e);
        }
    }

    @Override
    @Transactional
    public TournamentBO addTeam(Long teamId, Long tournamentId) throws BusinessException {
        try {
            TeamBO team = modelToBOConverter.teamModelToBO(teamDAO.findById(teamId));
            TournamentBO tournament = modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(tournamentId));
            List<TeamBO> teams = new ArrayList<>();
            if (tournament.getTeams() == null) {
                teams.add(team);
                tournament.setTeams(teams);
            } else if (tournament.getTeams().size() >= tournament.getSize()) {
                log.error("Full tournament");
                throw new BusinessException("Full tournament");
            } else if (tournament.getTeams().contains(team)) {
                log.error("This team is already on the tournament");
                throw new BusinessException("This team is already on the tournament");
            } else if (!(tournament.getModality().equals(team.getModality()))) {
                log.error("Modality is different");
                throw new BusinessException("Modality is different");
            } else {
                teams = tournament.getTeams();
                for (TeamBO t : teams) {
                    for (UserBOAux u : team.getUsers()) {
                        if (t.getUsers().contains(u)) {
                            log.error("A player on the team is already on another participating team ");
                            throw new BusinessException(
                                    "A player on the team is already on another participating team ");
                        }
                    }
                }
                teams.add(team);
                tournament.setTeams(teams);
            }

            return modelToBOConverter
                    .tournamentModelToBO(tournamentDAO.save(boToModelConverter.tournamentBOToModel(tournament)));
        } catch (EntityNotFoundException e) {
            log.error("This tournament not exists", e);
            throw new TournamentNotFoundException("This tournament not exists", e);
        } catch (DataException e) {
            log.error("Team could not be added", e);
            throw new BusinessException("Team could not be added", e);
        }
    }
}
