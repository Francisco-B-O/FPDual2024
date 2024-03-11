package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;

import java.util.List;

/**
 * Interface containing the TeamDAO methods.
 * This interface provides methods for interacting with team data in the data access layer.
 * Implementations of this interface handle the storage, retrieval, and deletion of team entities.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation to save a team
 *     Team savedTeam = teamDAO.save(newTeam);
 *
 *     // Data access operation to find a team by ID
 *     Team foundTeam = teamDAO.findById(teamId);
 *
 *     // Data access operation to retrieve a list of all teams
 *     List<Team> allTeams = teamDAO.findAll();
 *
 *     // Data access operation to delete a team by ID
 *     teamDAO.delete(teamId);
 *
 *     // Data access operation to find teams by name
 *     List<Team> teamsByName = teamDAO.findByName("teamName");
 *
 *     // Data access operation to find teams by modality
 *     List<Team> teamsByModality = teamDAO.findByModality(modality);
 *
 *     // Data access operation to find teams by captain
 *     List<Team> teamsByCaptain = teamDAO.findByCaptain(captain);
 * } catch (DataException e) {
 *     // Handle the exception
 *     logger.error("Data access error: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The interface defines methods for saving, finding by ID, finding all, deleting by ID, finding by name, finding by modality, and finding by captain.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface TeamDAO {
    /**
     * Saves a team.
     *
     * @param team Team to save.
     * @return Saved team.
     * @throws DataException If there is an issue saving the team.
     */
    Team save(Team team) throws DataException;

    /**
     * Finds a team by its ID.
     *
     * @param id The ID of the team to find.
     * @return The team found.
     * @throws DataException If there is an issue retrieving the team.
     */
    Team findById(Long id) throws DataException;

    /**
     * Retrieves a list of all teams.
     *
     * @return A list with all the teams.
     * @throws DataException If there is an issue retrieving the teams.
     */
    List<Team> findAll() throws DataException;

    /**
     * Deletes a team by its ID.
     *
     * @param id The ID of the team to be deleted.
     * @throws DataException If there is an issue deleting the team.
     */
    void delete(Long id) throws DataException;

    /**
     * Finds a list of teams with a specified name.
     *
     * @param name The name of the teams to find.
     * @return A list with found teams.
     * @throws DataException If there is an issue retrieving the teams by name.
     */
    List<Team> findByName(String name) throws DataException;

    /**
     * Finds a list of teams with a specified modality.
     *
     * @param modality The modality of the teams to find.
     * @return A list with found teams.
     * @throws DataException If there is an issue retrieving the teams by modality.
     */
    List<Team> findByModality(Modality modality) throws DataException;

    /**
     * Finds a list of teams with a specified captain.
     *
     * @param captain The captain of the teams to find.
     * @return A list with found teams.
     * @throws DataException If there is an issue retrieving the teams by captain.
     */
    List<Team> findByCaptain(User captain) throws DataException;
}
