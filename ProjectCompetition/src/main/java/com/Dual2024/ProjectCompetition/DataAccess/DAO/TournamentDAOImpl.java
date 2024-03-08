package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TournamentRepository;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * TournamentDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 */
@Repository
public class TournamentDAOImpl implements TournamentDAO {
    @Autowired
    private TournamentRepository tournamentRepository;

    /**
     * Implementation of the save method
     *
     * @param tournament Tournament to save
     * @return Saved tournament
     * @throws DataException
     */
    @Override
    public Tournament save(Tournament tournament) throws DataException {
        try {
            return tournamentRepository.save(tournament);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Tournament not saved", nre);
        }

    }

    /**
     * Implementation of the findById method
     *
     * @param id The id of the tournament you are looking for
     * @return The tournament found
     * @throws DataException
     */
    @Override
    public Tournament findById(Long id) throws DataException {
        try {
            Optional<Tournament> tournament = tournamentRepository.findById(id);
            if (tournament.isPresent()) {
                return tournament.get();
            } else {
                throw new NotFoundException("Tournament not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findAll method
     *
     * @return A list with all the tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findAll() throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findAll();
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the delete method
     *
     * @param id The id of the tournament to be deleted
     * @throws DataException
     */
    @Override
    public void delete(Long id) throws DataException {
        try {
            tournamentRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Tournament not deleted", nre);
        }

    }

    /**
     * Implementation of the finByName method
     *
     * @param name The name of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findByName(String name) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByName(name);
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findByFormat method
     *
     * @param format The format of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findByFormat(Format format) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByFormat(format);
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findBySize method
     *
     * @param size The size of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findBySize(int size) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findBySize(size);
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findByStartDate method
     *
     * @param startDate The start date of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findByStartDate(LocalDateTime startDate) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByStartDate(startDate);
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findByEndDate method
     *
     * @param endDate The end date of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findByEndDate(LocalDateTime endDate) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByEndDate(endDate);
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findByState method
     *
     * @param state The state of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findByState(TournamentState state) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByState(state);
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findByModality method
     *
     * @param modality The modality of the tournaments you are looking for
     * @return A list with found tournaments
     * @throws DataException
     */
    @Override
    public List<Tournament> findByModality(Modality modality) throws DataException {
        try {
            List<Tournament> tournaments = tournamentRepository.findByModality(modality);
            if (tournaments.isEmpty()) {
                throw new NotFoundException("Tournaments not found");
            } else {
                return tournaments;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }
}
