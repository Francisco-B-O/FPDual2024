package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.User;
import com.dual2024.projectcompetition.utils.UserState;

import java.util.List;

/**
 * Interface containing the UserDAO methods.
 * This interface provides methods for interacting with user data in the data access layer.
 * Implementations of this interface handle the storage, retrieval, and deletion of user entities.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation to save a user
 *     User savedUser = userDAO.save(newUser);
 *
 *     // Data access operation to find a user by ID
 *     User foundUser = userDAO.findById(userId);
 *
 *     // Data access operation to retrieve a list of all users
 *     List<User> allUsers = userDAO.findAll();
 *
 *     // Data access operation to delete a user by ID
 *     userDAO.delete(userId);
 *
 *     // Data access operation to find a user by nickname
 *     User userByNick = userDAO.findByNick("nickname");
 *
 *     // Data access operation to find a user by email
 *     User userByEmail = userDAO.findByEmail("user@example.com");
 *
 *     // Data access operation to find a list of users with a specified state
 *     List<User> usersByState = userDAO.findByState(UserState.CONECTADO;
 *
 *     // Data access operation to find a list of users with a specified nickname or email address
 *     List<User> usersByNickOrEmail = userDAO.findByNickOrEmail("nickname", "user@example.com");
 * } catch (DataException e) {
 *     // Handle the exception
 *     logger.error("Data access error: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The interface defines methods for saving, finding by ID, finding all, deleting by ID, finding by nickname, finding by email, finding by state, and finding by nickname or email.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface UserDAO {
    /**
     * Saves a user.
     *
     * @param user {@link User} User to save.
     * @return {@link User} Saved user.
     * @throws DataException If there is an issue saving the user.
     */
    User save(User user) throws DataException;

    /**
     * Finds a user by its ID.
     *
     * @param id {@link Long} The ID of the user to find.
     * @return {@link User} The user found.
     * @throws DataException If there is an issue retrieving the user.
     */
    User findById(Long id) throws DataException;

    /**
     * Retrieves a list of all users.
     *
     * @return {@link List} A list with all the users.
     * @throws DataException If there is an issue retrieving the users.
     */
    List<User> findAll() throws DataException;

    /**
     * Deletes a user by its ID.
     *
     * @param id {@link Long} The ID of the user to be deleted.
     * @throws DataException If there is an issue deleting the user.
     */
    void delete(Long id) throws DataException;

    /**
     * Finds a user by their nickname.
     *
     * @param nick {@link String} The nickname of the user to find.
     * @return {@link User} The user found.
     * @throws DataException If there is an issue retrieving the user.
     */
    User findByNick(String nick) throws DataException;

    /**
     * Finds a user by their email address.
     *
     * @param email {@link String} The email address of the user to find.
     * @return {@link User} The user found.
     * @throws DataException If there is an issue retrieving the user.
     */
    User findByEmail(String email) throws DataException;

    /**
     * Finds a list of users with a specified state.
     *
     * @param state {@link UserState} The state of the users to find.
     * @return {@link List} A list with found users.
     * @throws DataException If there is an issue retrieving the users.
     */
    List<User> findByState(UserState state) throws DataException;

    /**
     * Finds a list of users with a specified nickname or email address.
     *
     * @param nick  {@link String} The nickname of the user to find.
     * @param email {@link String} The email address of the user to find.
     * @return {@link List} A list with found users.
     * @throws DataException If there is an issue retrieving the users.
     */
    List<User> findByNickOrEmail(String nick, String email) throws DataException;
}
