package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
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

    /**
     * Implementation of the save method
     *
     * @param team Team to save
     * @return Saved team
     * @throws DataException
     */
    @Override
    public Team save(Team team) throws DataException {
        try {
            return teamRepository.save(team);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Team not saved", nre);
        }

    }

    /**
     * Implementation of the findById method
     *
     * @param id The id of the team you are looking for
     * @return The team found
     * @throws DataException
     */
    @Override
    public Team findById(Long id) throws DataException {
        try {
            Optional<Team> team = teamRepository.findById(id);
            if (team.isPresent()) {
                return team.get();
            } else {
                throw new NotFoundException("Team not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findAll method
     *
     * @return A list with all the teams
     * @throws DataException
     */
    @Override
    public List<Team> findAll() throws DataException {
        try {
            List<Team> teams = teamRepository.findAll();
            if (teams.isEmpty()) {
                throw new NotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the delete method
     *
     * @param id The id of the team to be deleted
     * @throws DataException
     */
    @Override
    public void delete(Long id) throws DataException {
        try {
            teamRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Team not deleted", nre);
        }

    }

    /**
     * Implementation of the findByName method
     *
     * @param name The name of the teams you are looking for
     * @return A list with found teams
     * @throws DataException
     */
    @Override
    public List<Team> findByName(String name) throws DataException {
        try {
            List<Team> teams = teamRepository.findByName(name);
            if (teams.isEmpty()) {
                throw new NotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

    /**
     * Implementation of the findByModality method
     *
     * @param modality The modality of the teams you are looking for
     * @return A list with found teams
     * @throws DataException
     */
    @Override
    public List<Team> findByModality(Modality modality) throws DataException {
        try {
            List<Team> teams = teamRepository.findByModality(modality);
            if (teams.isEmpty()) {
                throw new NotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

    /**
     * Implementation of the findByCaptain method
     *
     * @param captain The captain of the teams you are looking for
     * @return A list with found teams
     * @throws DataException
     */
    @Override
    public List<Team> findByCaptain(User captain) throws DataException {
        try {
            List<Team> teams = teamRepository.findByCaptain(captain);
            if (teams.isEmpty()) {
                throw new NotFoundException("Teams not found");
            } else {
                return teams;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

}
