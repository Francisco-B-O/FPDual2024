package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface that contains the methods of the tournament service.
 *
 * @author Francisco Balonero Olivera
 */
public interface TournamentService {
    /**
     * Register tournament.
     *
     * @param tournamentBO The tournament bo
     * @return The saved tournament bo
     * @throws BusinessException
     */
    TournamentBO registerTournament(TournamentBO tournamentBO) throws BusinessException;

    /**
     * Gets tournament by id.
     *
     * @param id The id
     * @return The tournament by id
     * @throws BusinessException
     */
    TournamentBO getTournamentById(Long id) throws BusinessException;

    /**
     * Gets all tournaments.
     *
     * @return All tournaments
     * @throws BusinessException
     */
    List<TournamentBO> getAllTournaments() throws BusinessException;

    /**
     * Delete tournament.
     *
     * @param id The id
     * @throws BusinessException
     */
    void deleteTournament(Long id) throws BusinessException;

    /**
     * Gets tournaments by name.
     *
     * @param name The name
     * @return The tournaments by name
     * @throws BusinessException
     */
    List<TournamentBO> getTournamentsByName(String name) throws BusinessException;

    /**
     * Gets tournaments by format.
     *
     * @param formatBO The format bo
     * @return The tournaments by format
     * @throws BusinessException
     */
    List<TournamentBO> getTournamentsByFormat(FormatBO formatBO) throws BusinessException;

    /**
     * Gets tournaments by size.
     *
     * @param size The size
     * @return The tournaments by size
     * @throws BusinessException
     */
    List<TournamentBO> getTournamentsBySize(int size) throws BusinessException;

    /**
     * Gets tournaments by start date.
     *
     * @param startDate The start date
     * @return The tournaments by start date
     * @throws BusinessException
     */
    List<TournamentBO> getTournamentsByStartDate(LocalDateTime startDate) throws BusinessException;

    /**
     * Gets tournaments by end date.
     *
     * @param endDate The end date
     * @return The tournaments by end date
     * @throws BusinessException
     */
    List<TournamentBO> getTournamentsByEndDate(LocalDateTime endDate) throws BusinessException;

    /**
     * Gets tournaments by state.
     *
     * @param state The state
     * @return The tournaments by state
     * @throws BusinessException
     */
    List<TournamentBO> getTournamentsByState(TournamentState state) throws BusinessException;

    /**
     * Gets tournaments by modality.
     *
     * @param modalityBO The modality bo
     * @return The tournaments by modality
     * @throws BusinessException
     */
    List<TournamentBO> getTournamentsByModality(ModalityBO modalityBO) throws BusinessException;

    /**
     * Update tournament.
     *
     * @param tournamentBO The tournament bo
     * @return The updated tournament bo
     * @throws BusinessException
     */
    TournamentBO updateTournament(TournamentBO tournamentBO) throws BusinessException;

    /**
     * Add team into a tournament.
     *
     * @param teamId       The team id
     * @param tournamentId The tournament id
     * @return The tournament bo with the addedTeam
     * @throws BusinessException
     */
    TournamentBO addTeam(Long teamId, Long tournamentId) throws BusinessException;
}
