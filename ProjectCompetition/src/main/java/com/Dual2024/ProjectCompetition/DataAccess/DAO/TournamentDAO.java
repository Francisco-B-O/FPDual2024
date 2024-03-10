package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface containing the TournamentDAO methods.
 * This interface provides methods for interacting with tournament data in the data access layer.
 * Implementations of this interface handle the storage, retrieval, and deletion of tournament entities.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation to save a tournament
 *     Tournament savedTournament = tournamentDAO.save(newTournament);
 *
 *     // Data access operation to find a tournament by ID
 *     Tournament foundTournament = tournamentDAO.findById(tournamentId);
 *
 *     // Data access operation to retrieve a list of all tournaments
 *     List<Tournament> allTournaments = tournamentDAO.findAll();
 *
 *     // Data access operation to delete a tournament by ID
 *     tournamentDAO.delete(tournamentId);
 *
 *     // Data access operation to find tournaments by name
 *     List<Tournament> tournamentsByName = tournamentDAO.findByName("tournamentName");
 *
 *     // Data access operation to find tournaments by format
 *     List<Tournament> tournamentsByFormat = tournamentDAO.findByFormat(format);
 *
 *     // Data access operation to find tournaments by size
 *     List<Tournament> tournamentsBySize = tournamentDAO.findBySize(16);
 *
 *     // Data access operation to find tournaments by start date
 *     List<Tournament> tournamentsByStartDate = tournamentDAO.findByStartDate(LocalDateTime.now());
 *
 *     // Data access operation to find tournaments by end date
 *     List<Tournament> tournamentsByEndDate = tournamentDAO.findByEndDate(LocalDateTime.now().plusDays(7));
 *
 *     // Data access operation to find tournaments by state
 *     List<Tournament> tournamentsByState = tournamentDAO.findByState(TournamentState.APLAZADO);
 *
 *     // Data access operation to find tournaments by modality
 *     List<Tournament> tournamentsByModality = tournamentDAO.findByModality(modality);
 * } catch (DataException e) {
 *     // Handle the exception
 *     logger.error("Data access error: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The interface defines methods for saving, finding by ID, finding all, deleting by ID, finding by name, finding by format, finding by size, finding by start date, finding by end date, finding by state, and finding by modality.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface TournamentDAO {
    /**
     * Saves a tournament.
     *
     * @param tournament Tournament to save.
     * @return Saved tournament.
     * @throws DataException If there is an issue saving the tournament.
     */
    Tournament save(Tournament tournament) throws DataException;

    /**
     * Finds a tournament by its ID.
     *
     * @param id The ID of the tournament to find.
     * @return The tournament found.
     * @throws DataException If there is an issue retrieving the tournament.
     */
    Tournament findById(Long id) throws DataException;

    /**
     * Retrieves a list of all tournaments.
     *
     * @return A list with all the tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findAll() throws DataException;

    /**
     * Deletes a tournament by its ID.
     *
     * @param id The ID of the tournament to be deleted.
     * @throws DataException If there is an issue deleting the tournament.
     */
    void delete(Long id) throws DataException;

    /**
     * Finds tournaments by their name.
     *
     * @param name The name of the tournaments to find.
     * @return A list with found tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findByName(String name) throws DataException;

    /**
     * Finds tournaments by their format.
     *
     * @param format The format of the tournaments to find.
     * @return A list with found tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findByFormat(Format format) throws DataException;

    /**
     * Finds tournaments by their size.
     *
     * @param size The size of the tournaments to find.
     * @return A list with found tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findBySize(int size) throws DataException;

    /**
     * Finds tournaments by their start date.
     *
     * @param startDate The start date of the tournaments to find.
     * @return A list with found tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findByStartDate(LocalDateTime startDate) throws DataException;

    /**
     * Finds tournaments by their end date.
     *
     * @param endDate The end date of the tournaments to find.
     * @return A list with found tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findByEndDate(LocalDateTime endDate) throws DataException;

    /**
     * Finds tournaments by their state.
     *
     * @param state The state of the tournaments to find.
     * @return A list with found tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findByState(TournamentState state) throws DataException;

    /**
     * Finds tournaments by their modality.
     *
     * @param modality The modality of the tournaments to find.
     * @return A list with found tournaments.
     * @throws DataException If there is an issue retrieving the tournaments.
     */
    List<Tournament> findByModality(Modality modality) throws DataException;
}
