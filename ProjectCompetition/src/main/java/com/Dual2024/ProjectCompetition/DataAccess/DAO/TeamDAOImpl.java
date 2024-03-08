package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TeamRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * TeamDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 */
@Repository
public class TeamDAOImpl implements TeamDAO {
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public Team save(Team team) throws DataException {
        try {
            return teamRepository.save(team);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Team not saved", nre);
        }

    }


    @Override
    public Team findById(Long id) throws DataException {
        try {
            Optional<Team> team = teamRepository.findById(id);
            if (team.isPresent()) {
                return team.get();
            } else {
                throw new EntityNotFoundException("Team not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    @Override
    public List<Team> findAll() throws DataException {
        try {
            List<Team> teams = teamRepository.findAll();
            if (teams.isEmpty()) {
                throw new EntityNotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }


    @Override
    public void delete(Long id) throws DataException {
        try {
            teamRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Team not deleted", nre);
        }

    }


    @Override
    public List<Team> findByName(String name) throws DataException {
        try {
            List<Team> teams = teamRepository.findByName(name);
            if (teams.isEmpty()) {
                throw new EntityNotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }


    @Override
    public List<Team> findByModality(Modality modality) throws DataException {
        try {
            List<Team> teams = teamRepository.findByModality(modality);
            if (teams.isEmpty()) {
                throw new EntityNotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }


    @Override
    public List<Team> findByCaptain(User captain) throws DataException {
        try {
            List<Team> teams = teamRepository.findByCaptain(captain);
            if (teams.isEmpty()) {
                throw new EntityNotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

}
