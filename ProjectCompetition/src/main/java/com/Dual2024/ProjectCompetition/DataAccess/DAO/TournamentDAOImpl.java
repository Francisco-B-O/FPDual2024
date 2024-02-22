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
			List<Tournament> tournaments = tournamentRepository.findAll();
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
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
	public List<Tournament> findByName(String name) throws DataException {
		try {
			List<Tournament> tournaments = tournamentRepository.findByName(name);
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
		}

	}

	@Override
	public List<Tournament> findByFormat(Format format) throws DataException {
		try {
			List<Tournament> tournaments = tournamentRepository.findByFormat(format);
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
		}

	}

	@Override
	public List<Tournament> findBySize(int size) throws DataException {
		try {
			List<Tournament> tournaments = tournamentRepository.findBySize(size);
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
		}

	}

	@Override
	public List<Tournament> findByStartDate(LocalDateTime startDate) throws DataException {
		try {
			List<Tournament> tournaments = tournamentRepository.findByStartDate(startDate);
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
		}

	}

	@Override
	public List<Tournament> findByEndDate(LocalDateTime endDate) throws DataException {
		try {
			List<Tournament> tournaments = tournamentRepository.findByEndDate(endDate);
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
		}

	}

	@Override
	public List<Tournament> findByState(TournamentState state) throws DataException {
		try {
			List<Tournament> tournaments = tournamentRepository.findByState(state);
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
		}

	}

	@Override
	public List<Tournament> findByModality(Modality modality) throws DataException {
		try {
			List<Tournament> tournaments = tournamentRepository.findByModality(modality);
			if (tournaments.isEmpty()) {
				throw new DataException("Tournaments not found");
			} else {
				return tournaments;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Tournaments not found");
		}

	}
}
