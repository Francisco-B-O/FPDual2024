package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;

import java.util.List;

/**
 * Service interface containing methods for managing teams.
 *
 * <p>This interface declares methods for registering teams, retrieving teams by various criteria,
 * updating teams, and adding players to teams.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Obtain an instance of TeamService
 * TeamService teamService = // instantiate or inject the implementation
 *
 * // Register a new team
 * TeamBO newTeam = // create a new team instance
 * Long captainId = // obtain the captain's user ID
 * TeamBO registeredTeam = teamService.registerTeam(captainId, newTeam);
 *
 * // Retrieve a team by ID
 * Long teamId = // obtain the team's ID
 * TeamBO retrievedTeam = teamService.getTeamById(teamId);
 *
 * // Retrieve all teams
 * List<TeamBO> allTeams = teamService.getAllTeams();
 *
 * // Delete a team
 * Long teamToDeleteId = // obtain the ID of the team to delete
 * teamService.deleteTeam(teamToDeleteId);
 *
 * // Retrieve teams by name
 * String teamName = // obtain the team name
 * List<TeamBO> teamsByName = teamService.getTeamsByName(teamName);
 *
 * // Retrieve teams by modality
 * ModalityBO modality = // obtain the modality
 * List<TeamBO> teamsByModality = teamService.getTeamsByModality(modality);
 *
 * // Update a team
 * TeamBO teamToUpdate = // obtain the team to update
 * TeamBO updatedTeam = teamService.updateTeam(teamToUpdate);
 *
 * // Add a player to a team
 * Long playerId = // obtain the player's user ID
 * Long teamIdToAddPlayer = // obtain the team's ID
 * TeamBO teamWithAddedPlayer = teamService.addPlayer(playerId, teamIdToAddPlayer);
 * }
 * </pre>
 *
 * <p>Implementing classes should handle {@link BusinessException} to appropriately manage exceptions
 * related to business operations on teams.</p>
 *
 * <p>Instances of this interface are typically used in the business layer of the application to provide
 * a clean separation between the business logic and data access logic.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface TeamService {
    /**
     * Registers a new team.
     *
     * @param captainId The ID of the team captain
     * @param teamBO    The team BO to register
     * @return The registered team BO
     * @throws BusinessException If an error occurs during the operation
     */
    TeamBO registerTeam(Long captainId, TeamBO teamBO) throws BusinessException;

    /**
     * Retrieves a team by ID.
     *
     * @param id The ID of the team to retrieve
     * @return The team BO with the specified ID
     * @throws BusinessException If an error occurs during the operation
     */
    TeamBO getTeamById(Long id) throws BusinessException;

    /**
     * Retrieves all teams.
     *
     * @return A list of all teams
     * @throws BusinessException If an error occurs during the operation
     */
    List<TeamBO> getAllTeams() throws BusinessException;

    /**
     * Deletes a team.
     *
     * @param id The ID of the team to delete
     * @throws BusinessException If an error occurs during the operation
     */
    void deleteTeam(Long id) throws BusinessException;

    /**
     * Retrieves teams by name.
     *
     * @param name The name of the teams to retrieve
     * @return A list of teams with the specified name
     * @throws BusinessException If an error occurs during the operation
     */
    List<TeamBO> getTeamsByName(String name) throws BusinessException;

    /**
     * Retrieves teams by modality.
     *
     * @param modalityBO The modality of the teams to retrieve
     * @return A list of teams with the specified modality
     * @throws BusinessException If an error occurs during the operation
     */
    List<TeamBO> getTeamsByModality(ModalityBO modalityBO) throws BusinessException;

    /**
     * Updates a team.
     *
     * @param teamBO The team BO to update
     * @return The updated team BO
     * @throws BusinessException If an error occurs during the operation
     */
    TeamBO updateTeam(TeamBO teamBO) throws BusinessException;

    /**
     * Adds a player to a team.
     *
     * @param userId The ID of the user to add
     * @param teamId The ID of the team to which the user will be added
     * @return The team BO with the added player
     * @throws BusinessException If an error occurs during the operation
     */
    TeamBO addPlayer(Long userId, Long teamId) throws BusinessException;
}
