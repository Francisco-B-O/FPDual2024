package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Format;
import com.dual2024.projectcompetition.dataaccess.model.Modality;
import com.dual2024.projectcompetition.dataaccess.model.Tournament;
import com.dual2024.projectcompetition.dataaccess.repository.TournamentRepository;
import com.dual2024.projectcompetition.utils.TournamentState;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TournamentDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 * @see TournamentDAO
 */
@Slf4j
@Repository
public class TournamentDAOImpl implements TournamentDAO {
    @Autowired
    private TournamentRepository tournamentRepository;


    @Override
    public Tournament save(Tournament tournament) throws DataException {
        try {
            Tournament savedTournament = tournamentRepository.save(tournament);
            log.info("Tournament saved successfully with id: {}", savedTournament.getId());
            return savedTournament;
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            log.error("Error saving tournament", nre);
            throw new DataException("Tournament not saved", nre);
        }
    }

    @Override
    public Tournament findById(Long id) throws DataException {
        try {
            Tournament tournament = tournamentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
            log.info("Tournament retrieved successfully with id: {}", id);
            return tournament;
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournament by id: {}", id, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Tournament> findAll() throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findAll();
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found");
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments", tournaments.size());
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find all tournaments", nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public void delete(Long id) throws DataException {
        try {
            tournamentRepository.deleteById(id);
            log.info("Tournament deleted successfully with id: {}", id);
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to delete tournament with id: {}", id, nre);
            throw new DataException("Tournament not deleted", nre);
        }
    }

    @Override
    public List<Tournament> findByName(String name) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByName(name);
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found with name: {}", name);
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments with name: {}", tournaments.size(), name);
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournaments by name: {}", name, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Tournament> findByFormat(Format format) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByFormat(format);
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found with format: {}", format);
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments with format: {}", tournaments.size(), format);
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournaments by format: {}", format, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Tournament> findBySize(int size) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findBySize(size);
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found with size: {}", size);
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments with size: {}", tournaments.size(), size);
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournaments by size: {}", size, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Tournament> findByStartDate(LocalDateTime startDate) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByStartDate(startDate);
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found with start date: {}", startDate);
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments with start date: {}", tournaments.size(), startDate);
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournaments by start date: {}", startDate, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Tournament> findByEndDate(LocalDateTime endDate) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByEndDate(endDate);
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found with end date: {}", endDate);
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments with end date: {}", tournaments.size(), endDate);
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournaments by end date: {}", endDate, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Tournament> findByState(TournamentState state) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByState(state);
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found with state: {}", state);
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments with state: {}", tournaments.size(), state);
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournaments by state: {}", state, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Tournament> findByModality(Modality modality) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByModality(modality);
            if (tournaments.isEmpty()) {
                log.warn("No tournaments found with modality: {}", modality);
                throw new EntityNotFoundException("Tournaments not found");
            } else {
                log.info("Found {} tournaments with modality: {}", tournaments.size(), modality);
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find tournaments by modality: {}", modality, nre);
            throw new DataException("Data access error", nre);
        }
    }
}
