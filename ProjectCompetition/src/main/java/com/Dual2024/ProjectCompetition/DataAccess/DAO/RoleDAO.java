package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

import java.util.List;

/**
 * Interface containing the RoleDAO methods
 *
 * @author Francisco Balonero Olivera
 */
public interface RoleDAO {
    /**
     * Method that saves a role
     *
     * @param role Role to save
     * @return Saved role
     * @throws DataException
     */
    Role save(Role role) throws DataException;

    /**
     * Method that searches a role by id
     *
     * @param id The id of the role you are looking for
     * @return The role found
     * @throws DataException
     */
    Role findById(Long id) throws DataException;

    /**
     * Method that returns a list with all the modalities
     *
     * @return A list with all the roles
     * @throws DataException
     */
    List<Role> findAll() throws DataException;

    /**
     * Method that deletes a role by id
     *
     * @param id The id of the role to be deleted
     * @throws DataException
     */
    void delete(Long id) throws DataException;

    /**
     * Method that searches a role by name
     *
     * @param name The name of the role you are looking for
     * @return The role found
     * @throws DataException
     */
    Role findByName(String name) throws DataException;
}
