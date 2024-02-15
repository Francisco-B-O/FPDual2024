package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TournamentRepository;

import jakarta.validation.ConstraintViolationException;

@Component
public class TournamentDAOImpl implements TournamentDAO {
	@Autowired
	private TournamentRepository tournamentRepository;

	@Override
	public Tournament save(Tournament tournament) throws DataException {
		try {
			return tournamentRepository.save(tournament);
		} catch (DataIntegrityViolationException dive) {
			throw new DataException("Tournament not saved", dive);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournament not saved", nre);
		} catch (ConstraintViolationException cve) {
			throw new DataException("Tournament not saved", cve);
		}

	}

	@Override
	public Tournament findById(Long id) throws DataException {
		try {
			Optional<Tournament> tournament = tournamentRepository.findById(id);
			if (tournament.isPresent()) {
				return tournament.get();
			} else {
				throw new DataException("Tournament not found");
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournament not found", nre);
		}

	}

	@Override
	public List<Tournament> findAll() throws DataException {
		try {
			return tournamentRepository.findAll();
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found", nre);
		}

	}

	@Override
	public void delete(Tournament tournament) throws DataException {
		try {
			if (tournament.equals(null)) {
				throw new DataException("Tournament not deleted");
			} else {
				tournamentRepository.delete(tournament);
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournament not deleted", nre);
		}

	}

	@Override
	public Tournament findByName(String name) throws DataException {
		try {
			Tournament tournament = tournamentRepository.findByName(name);
			if (tournament == null) {
				throw new DataException("Tournament not found");
			} else {
				return tournament;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournament not found", nre);
		}
	}

	@Override
	public List<Tournament> findByFormat(Format format) throws DataException {
		try {
			return tournamentRepository.findByFormat(format);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found", nre);
		}

	}

	@Override
	public List<Tournament> findBySize(int size) throws DataException {
		try {
			return tournamentRepository.findBySize(size);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found", nre);
		}

	}

	@Override
	public List<Tournament> findByStartDate(LocalDateTime startDate) throws DataException {
		try {
			return tournamentRepository.findByStartDate(startDate);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found", nre);
		}

	}

	@Override
	public List<Tournament> findByEndDate(LocalDateTime endDate) throws DataException {
		try {
			return tournamentRepository.findByEndDate(endDate);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found", nre);
		}

	}

	@Override
	public List<Tournament> findByState(TournamentState state) throws DataException {
		try {
			return tournamentRepository.findByState(state);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found", nre);
		}

	}

	@Override
	public List<Tournament> findByModality(Modality modality) throws DataException {
		try {
			return tournamentRepository.findByModality(modality);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found", nre);
		}

	}
}
