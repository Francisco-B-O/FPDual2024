package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface containing methods for managing tournaments.
 *
 * <p>This interface declares methods for registering tournaments, retrieving tournaments by various criteria,
 * updating tournaments, and adding teams to tournaments.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Obtain an instance of TournamentService
 * TournamentService tournamentService = // instantiate or inject the implementation
 *
 * // Register a new tournament
 * TournamentBO newTournament = // create a new tournament instance
 * TournamentBO registeredTournament = tournamentService.registerTournament(newTournament);
 *
 * // Retrieve a tournament by ID
 * Long tournamentId = // obtain the tournament's ID
 * TournamentBO retrievedTournament = tournamentService.getTournamentById(tournamentId);
 *
 * // Retrieve all tournaments
 * List<TournamentBO> allTournaments = tournamentService.getAllTournaments();
 *
 * // Delete a tournament
 * Long tournamentToDeleteId = // obtain the ID of the tournament to delete
 * tournamentService.deleteTournament(tournamentToDeleteId);
 *
 * // Retrieve tournaments by name
 * String tournamentName = // obtain the tournament name
 * List<TournamentBO> tournamentsByName = tournamentService.getTournamentsByName(tournamentName);
 *
 * // Retrieve tournaments by format
 * FormatBO format = // obtain the format
 * List<TournamentBO> tournamentsByFormat = tournamentService.getTournamentsByFormat(format);
 *
 * // Update a tournament
 * TournamentBO tournamentToUpdate = // obtain the tournament to update
 * TournamentBO updatedTournament = tournamentService.updateTournament(tournamentToUpdate);
 *
 * // Add a team to a tournament
 * Long teamId = // obtain the team's ID
 * Long tournamentIdToAddTeam = // obtain the tournament's ID
 * TournamentBO tournamentWithAddedTeam = tournamentService.addTeam(teamId, tournamentIdToAddTeam);
 * }
 * </pre>
 *
 * <p>Implementing classes should handle {@link BusinessException} to appropriately manage exceptions
 * related to business operations on tournaments.</p>
 *
 * <p>Instances of this interface are typically used in the business layer of the application to provide
 * a clean separation between the business logic and data access logic.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface TournamentService {
    /**
     * Registers a new tournament.
     *
     * @param tournamentBO The tournament BO to register
     * @return The registered tournament BO
     * @throws BusinessException If an error occurs during the operation
     */
    TournamentBO registerTournament(TournamentBO tournamentBO) throws BusinessException;

    /**
     * Retrieves a tournament by ID.
     *
     * @param id The ID of the tournament to retrieve
     * @return The tournament BO with the specified ID
     * @throws BusinessException If an error occurs during the operation
     */
    TournamentBO getTournamentById(Long id) throws BusinessException;

    /**
     * Retrieves all tournaments.
     *
     * @return A list of all tournaments
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getAllTournaments() throws BusinessException;

    /**
     * Deletes a tournament.
     *
     * @param id The ID of the tournament to delete
     * @throws BusinessException If an error occurs during the operation
     */
    void deleteTournament(Long id) throws BusinessException;

    /**
     * Retrieves tournaments by name.
     *
     * @param name The name of the tournaments to retrieve
     * @return A list of tournaments with the specified name
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getTournamentsByName(String name) throws BusinessException;

    /**
     * Retrieves tournaments by format.
     *
     * @param formatBO The format of the tournaments to retrieve
     * @return A list of tournaments with the specified format
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getTournamentsByFormat(FormatBO formatBO) throws BusinessException;

    /**
     * Retrieves tournaments by size.
     *
     * @param size The size of the tournaments to retrieve
     * @return A list of tournaments with the specified size
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getTournamentsBySize(int size) throws BusinessException;

    /**
     * Retrieves tournaments by start date.
     *
     * @param startDate The start date of the tournaments to retrieve
     * @return A list of tournaments with the specified start date
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getTournamentsByStartDate(LocalDateTime startDate) throws BusinessException;

    /**
     * Retrieves tournaments by end date.
     *
     * @param endDate The end date of the tournaments to retrieve
     * @return A list of tournaments with the specified end date
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getTournamentsByEndDate(LocalDateTime endDate) throws BusinessException;

    /**
     * Retrieves tournaments by state.
     *
     * @param state The state of the tournaments to retrieve
     * @return A list of tournaments with the specified state
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getTournamentsByState(TournamentState state) throws BusinessException;

    /**
     * Retrieves tournaments by modality.
     *
     * @param modalityBO The modality of the tournaments to retrieve
     * @return A list of tournaments with the specified modality
     * @throws BusinessException If an error occurs during the operation
     */
    List<TournamentBO> getTournamentsByModality(ModalityBO modalityBO) throws BusinessException;

    /**
     * Updates a tournament.
     *
     * @param tournamentBO The tournament BO to update
     * @return The updated tournament BO
     * @throws BusinessException If an error occurs during the operation
     */
    TournamentBO updateTournament(TournamentBO tournamentBO) throws BusinessException;

    /**
     * Adds a team to a tournament.
     *
     * @param teamId       The ID of the team to add
     * @param tournamentId The ID of the tournament to add the team to
     * @return The tournament BO with the added team
     * @throws BusinessException If an error occurs during the operation
     */
    TournamentBO addTeam(Long teamId, Long tournamentId) throws BusinessException;
}
