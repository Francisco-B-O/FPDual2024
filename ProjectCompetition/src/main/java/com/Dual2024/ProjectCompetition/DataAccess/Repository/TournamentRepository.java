package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for performing CRUD operations on Tournament.
 *
 * <p>This interface extends the JpaRepository interface, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on Tournament entities. The methods declared in this interface are automatically implemented by Spring Data JPA.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Data access operation to find all tournaments with the specified name
 * List<Tournament> tournamentsByName = tournamentRepository.findByName("TournamentName");
 *
 * // Data access operation to find all tournaments with the specified format
 * List<Tournament> tournamentsByFormat = tournamentRepository.findByFormat(format);
 *
 * // Data access operation to find all tournaments with the specified size
 * List<Tournament> tournamentsBySize = tournamentRepository.findBySize(16);
 *
 * // Data access operation to find all tournaments with the specified start date
 * List<Tournament> tournamentsByStartDate = tournamentRepository.findByStartDate(LocalDateTime.now());
 *
 * // Data access operation to find all tournaments with the specified end date
 * List<Tournament> tournamentsByEndDate = tournamentRepository.findByEndDate(LocalDateTime.now().plusDays(7));
 *
 * // Data access operation to find all tournaments with the specified state
 * List<Tournament> tournamentsByState = tournamentRepository.findByState(TournamentState.APLAZADO);
 *
 * // Data access operation to find all tournaments with the specified modality
 * List<Tournament> tournamentsByModality = tournamentRepository.findByModality(modality);
 * }
 * </pre>
 *
 * <p>The interface includes methods to find tournaments by name, format, size, start date, end date, state, and modality.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    /**
     * Finds all tournaments with the specified name.
     *
     * @param name the name of the tournament
     * @return a list of tournaments with the specified name
     */
    List<Tournament> findByName(String name);

    /**
     * Finds all tournaments with the specified format.
     *
     * @param format the format of the tournament
     * @return a list of tournaments with the specified format
     */
    List<Tournament> findByFormat(Format format);

    /**
     * Finds all tournaments with the specified size.
     *
     * @param size the size of the tournament
     * @return a list of tournaments with the specified size
     */
    List<Tournament> findBySize(int size);

    /**
     * Finds all tournaments with the specified start date.
     *
     * @param startDate the start date of the tournament
     * @return a list of tournaments with the specified start date
     */
    List<Tournament> findByStartDate(LocalDateTime startDate);

    /**
     * Finds all tournaments with the specified end date.
     *
     * @param endDate the end date of the tournament
     * @return a list of tournaments with the specified end date
     */
    List<Tournament> findByEndDate(LocalDateTime endDate);

    /**
     * Finds all tournaments with the specified state.
     *
     * @param state the state of the tournament
     * @return a list of tournaments with the specified state
     */
    List<Tournament> findByState(TournamentState state);

    /**
     * Finds all tournaments with the specified modality.
     *
     * @param modality the modality of the tournament
     * @return a list of tournaments with the specified modality
     */
    List<Tournament> findByModality(Modality modality);

}
