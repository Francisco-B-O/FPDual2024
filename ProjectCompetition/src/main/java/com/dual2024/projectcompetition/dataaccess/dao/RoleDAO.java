package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

import java.util.List;

/**
 * Interface containing the RoleDAO methods.
 * This interface provides methods for interacting with role data in the data access layer.
 * Implementations of this interface handle the storage, retrieval, and deletion of role entities.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation to save a role
 *     Role savedRole = roleDAO.save(newRole);
 *
 *     // Data access operation to find a role by ID
 *     Role foundRole = roleDAO.findById(roleId);
 *
 *     // Data access operation to retrieve a list of all roles
 *     List<Role> allRoles = roleDAO.findAll();
 *
 *     // Data access operation to delete a role by ID
 *     roleDAO.delete(roleId);
 *
 *     // Data access operation to find a role by name
 *     Role roleByName = roleDAO.findByName("roleName");
 * } catch (DataException e) {
 *     // Handle the exception
 *     logger.error("Data access error: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The interface defines methods for saving, finding by ID, finding all, deleting by ID, and finding by name.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface RoleDAO {
    /**
     * Saves a role.
     *
     * @param role Role to save.
     * @return Saved role.
     * @throws DataException If there is an issue saving the role.
     */
    Role save(Role role) throws DataException;

    /**
     * Finds a role by its ID.
     *
     * @param id The ID of the role to find.
     * @return The role found.
     * @throws DataException If there is an issue retrieving the role.
     */
    Role findById(Long id) throws DataException;

    /**
     * Retrieves a list of all roles.
     *
     * @return A list with all the roles.
     * @throws DataException If there is an issue retrieving the roles.
     */
    List<Role> findAll() throws DataException;

    /**
     * Deletes a role by its ID.
     *
     * @param id The ID of the role to be deleted.
     * @throws DataException If there is an issue deleting the role.
     */
    void delete(Long id) throws DataException;

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role to find.
     * @return The role found.
     * @throws DataException If there is an issue retrieving the role by name.
     */
    Role findByName(String name) throws DataException;
}
