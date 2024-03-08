package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;

import java.util.List;

/**
 * Interface that contains the methods of the team service.
 *
 * @author Francisco Balonero Olivera
 */
public interface TeamService {
    /**
     * Register team team bo.
     *
     * @param captainId The captain id
     * @param teamBO    The team bo
     * @return The team bo
     * @throws BusinessException
     */
    TeamBO registerTeam(Long captainId, TeamBO teamBO) throws BusinessException;

    /**
     * Gets team by id.
     *
     * @param id The id
     * @return The team by id
     * @throws BusinessException
     */
    TeamBO getTeamById(Long id) throws BusinessException;

    /**
     * Gets all teams.
     *
     * @return All teams
     * @throws BusinessException
     */
    List<TeamBO> getAllteams() throws BusinessException;

    /**
     * Delete team.
     *
     * @param id The id
     * @throws BusinessException
     */
    void deleteTeam(Long id) throws BusinessException;

    /**
     * Gets teams by name.
     *
     * @param name The name
     * @return The teams by name
     * @throws BusinessException
     */
    List<TeamBO> getTeamsByName(String name) throws BusinessException;

    /**
     * Gets teams by modality.
     *
     * @param modalityBO The modality bo
     * @return The teams by modality
     * @throws BusinessException
     */
    List<TeamBO> getTeamsByModality(ModalityBO modalityBO) throws BusinessException;

    /**
     * Update team.
     *
     * @param teamBO The team bo
     * @return The updated team bo
     * @throws BusinessException
     */
    TeamBO updateTeam(TeamBO teamBO) throws BusinessException;

    /**
     * Add user into a team.
     *
     * @param userId The user id
     * @param teamId The team id
     * @return The team bo with added user
     * @throws BusinessException
     */
    TeamBO addPlayer(Long userId, Long teamId) throws BusinessException;
}
