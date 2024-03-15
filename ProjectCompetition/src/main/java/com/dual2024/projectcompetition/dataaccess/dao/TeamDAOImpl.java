package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Modality;
import com.dual2024.projectcompetition.dataaccess.model.Team;
import com.dual2024.projectcompetition.dataaccess.model.User;
import com.dual2024.projectcompetition.dataaccess.repository.TeamRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TeamDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 * @see TeamDAO
 */
@Slf4j
@Repository
public class TeamDAOImpl implements TeamDAO {
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public Team save(Team team) throws DataException {
        try {
            Team savedTeam = teamRepository.save(team);
            log.debug("Team saved successfully with id: {}", savedTeam.getId());
            log.info("Team saved successfully with id");
            return savedTeam;
        } catch (NestedRuntimeException | ConstraintViolationException e) {
            log.error("Error saving team", e);
            throw new DataException("Team not saved", e);
        }
    }

    @Override
    public Team findById(Long id) throws DataException {
        try {
            Team team = teamRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Team not found"));
            log.debug("Team found by id: {}", id);
            log.info("Team found by id");
            return team;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding team by id: {}", id, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Team> findAll() throws DataException {
        try {
            List<Team> teams = teamRepository.findAll();
            if (teams.isEmpty()) {
                log.warn("No teams found");
                throw new EntityNotFoundException("Teams not found");
            } else {
                log.info("Found {} teams", teams.size());
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding all teams", nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public void delete(Long id) throws DataException {
        try {
            teamRepository.deleteById(id);
            log.debug("Team deleted successfully with id: {}", id);
            log.info("Team deleted successfully");
        } catch (NestedRuntimeException nre) {
            log.error("Error deleting team with id: {}", id, nre);
            throw new DataException("Team not deleted", nre);
        }
    }

    @Override
    public List<Team> findByName(String name) throws DataException {
        try {
            List<Team> teams = teamRepository.findByName(name);
            if (teams.isEmpty()) {
                log.warn("No teams found with name: {}", name);
                throw new EntityNotFoundException("Teams not found");
            } else {
                log.info("Found {} teams with name: {}", teams.size(), name);
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding teams by name: {}", name, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Team> findByModality(Modality modality) throws DataException {
        try {
            List<Team> teams = teamRepository.findByModality(modality);
            if (teams.isEmpty()) {
                log.warn("No teams found with modality: {}", modality);
                throw new EntityNotFoundException("Teams not found");
            } else {
                log.info("Found {} teams with modality: {}", teams.size(), modality);
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding teams by modality: {}", modality, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Team> findByCaptain(User captain) throws DataException {
        try {
            List<Team> teams = teamRepository.findByCaptain(captain);
            if (teams.isEmpty()) {
                log.warn("No teams found with captain: {}", captain);
                throw new EntityNotFoundException("Teams not found");
            } else {
                log.info("Found {} teams with captain: {}", teams.size(), captain);
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding teams by captain: {}", captain, nre);
            throw new DataException("Data access error", nre);
        }
    }

}
