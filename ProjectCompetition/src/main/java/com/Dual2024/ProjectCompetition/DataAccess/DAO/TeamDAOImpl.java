package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TeamRepository;

import jakarta.validation.ConstraintViolationException;

@Component
public class TeamDAOImpl implements TeamDAO {
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public Team save(Team team) throws DataException {
		try {
			return teamRepository.save(team);
		} catch (DataIntegrityViolationException dive) {
			throw new DataException("Team not saved", dive);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Team not saved", nre);
		} catch (ConstraintViolationException cve) {
			throw new DataException("Team not saved", cve);
		}

	}

	@Override
	public Team findById(Long id) throws DataException {
		try {
			Optional<Team> team = teamRepository.findById(id);
			if (team.isPresent()) {
				return team.get();
			} else {
				throw new DataException("Team not found");
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Team not found", nre);
		}

	}

	@Override
	public List<Team> findAll() throws DataException {
		try {
			List<Team> teams = teamRepository.findAll();
			if (teams.isEmpty()) {
				throw new DataException("Teams not found");
			} else {
				return teams;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Teams not found", nre);
		}

	}

	@Override
	public void delete(Team team) throws DataException {
		try {
			if (team.equals(null)) {
				throw new DataException("Team not deleted");
			} else {
				teamRepository.delete(team);
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Team not deleted", nre);
		}

	}

	@Override
	public List<Team> findByName(String name) throws DataException {
		try {
			List<Team> teams = teamRepository.findByName(name);
			if (teams.isEmpty()) {
				throw new DataException("Teams not found");
			} else {
				return teams;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Teams not found", nre);
		}
	}

	@Override
	public List<Team> findByModality(Modality modality) throws DataException {
		try {
			List<Team> teams = teamRepository.findByModality(modality);
			if (teams.isEmpty()) {
				throw new DataException("Teams not found");
			} else {
				return teams;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Teams not found", nre);
		}
	}
}
