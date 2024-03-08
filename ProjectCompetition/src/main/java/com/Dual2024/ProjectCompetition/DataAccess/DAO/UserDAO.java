package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.Utils.UserState;

import java.util.List;

/**
 * Interface containing the UserDAO methods
 *
 * @author Francisco Balonero Olivera
 */
public interface UserDAO {
    /**
     * Method that saves a user
     *
     * @param user User to save
     * @return Saved user
     * @throws DataException the data exception
     */
    User save(User user) throws DataException;

    /**
     * Method that searches a user by id
     *
     * @param id The id of the user you are looking for
     * @return The user found
     * @throws DataException the data exception
     */
    User findById(Long id) throws DataException;

    /**
     * Method that returns a list with all the users
     *
     * @return A list with all the users
     * @throws DataException the data exception
     */
    List<User> findAll() throws DataException;

    /**
     * Method that deletes a user by id
     *
     * @param id The id of the user to be deleted
     * @throws DataException the data exception
     */
    void delete(Long id) throws DataException;

    /**
     * Method that searches a user by name
     *
     * @param nick The nick of the user you are looking for
     * @return The user found
     * @throws DataException the data exception
     */
    User findByNick(String nick) throws DataException;

    /**
     * Method that searches a user by name
     *
     * @param email The email of the user you are looking for
     * @return The user found
     * @throws DataException the data exception
     */
    User findByEmail(String email) throws DataException;

    /**
     * Method that searches a list with users with this state
     *
     * @param state The state of the users you are looking for
     * @return A list with found users
     * @throws DataException the data exception
     */
    List<User> findByState(UserState state) throws DataException;

    /**
     * Method that searches a list with users with this nick or email
     *
     * @param nick  The nick of the user you are looking for
     * @param email The email of the user you are looking for
     * @return A list with found users
     * @throws DataException the data exception
     */
    List<User> findByNickOrEmail(String nick, String email) throws DataException;

}
