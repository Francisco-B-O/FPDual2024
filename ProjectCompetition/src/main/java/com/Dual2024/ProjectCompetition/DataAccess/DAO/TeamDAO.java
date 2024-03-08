package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;

import java.util.List;

/**
 * Interface containing the TeamDAO methods
 *
 * @author Francisco Balonero Olivera
 */
public interface TeamDAO {
    /**
     * Method that saves a team
     *
     * @param team Team to save
     * @return Saved team
     * @throws DataException
     */
    Team save(Team team) throws DataException;

    /**
     * Method that searches a team by id
     *
     * @param id The id of the team you are looking for
     * @return The team found
     * @throws DataException
     */
    Team findById(Long id) throws DataException;

    /**
     * Method that returns a list with all the teams
     *
     * @return A list with all the teams
     * @throws DataException
     */
    List<Team> findAll() throws DataException;

    /**
     * Method that deletes a team by id
     *
     * @param id The id of the team to be deleted
     * @throws DataException
     */
    void delete(Long id) throws DataException;

    /**
     * Method that searches a list with teams with this name
     *
     * @param name The name of the teams you are looking for
     * @return A list with found teams
     * @throws DataException
     */
    List<Team> findByName(String name) throws DataException;

    /**
     * Method that searches a list with teams with this modallity
     *
     * @param modality The modality of the teams you are looking for
     * @return A list with found teams
     * @throws DataException
     */
    List<Team> findByModality(Modality modality) throws DataException;

    /**
     * Method that searches a list with teams with this captain
     *
     * @param captain The captain of the teams you are looking for
     * @return A list with found teams
     * @throws DataException
     */
    List<Team> findByCaptain(User captain) throws DataException;
}
