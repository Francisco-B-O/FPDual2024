package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.UserRepository;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * UserDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository userRepository;

    /**
     * Implementation of the save method
     *
     * @param user User to save
     * @return Saved user
     * @throws DataException
     */
    @Override
    public User save(User user) throws DataException {
        try {
            return userRepository.save(user);
        } catch (ConstraintViolationException | NestedRuntimeException e) {
            throw new DataException("User not saved", e);
        }
    }

    /**
     * Implementation of the findById method
     *
     * @param id The id of the user you are looking for
     * @return The user found
     * @throws DataException
     */
    @Override
    public User findById(Long id) throws DataException {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new NotFoundException("User not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findAll method
     *
     * @return A list with all the users
     * @throws DataException
     */
    @Override
    public List<User> findAll() throws DataException {
        try {

            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new NotFoundException("Users not found");
            } else {
                return users;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the delete method
     *
     * @param id The id of the user to be deleted
     * @throws DataException
     */
    @Override
    public void delete(Long id) throws DataException {
        try {
            userRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("User not deleted", nre);
        }

    }

    /**
     * Implementation of the findByNick method
     *
     * @param nick The nick of the user you are looking for
     * @return The user found
     * @throws DataException
     */
    @Override
    public User findByNick(String nick) throws DataException {
        try {
            User user = userRepository.findByNick(nick);
            if (user == null) {
                throw new NotFoundException("User not found");
            } else {
                return user;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

    /**
     * Implementation of the findByEmail method
     *
     * @param email The email of the user you are looking for
     * @return The user found
     * @throws DataException
     */
    @Override
    public User findByEmail(String email) throws DataException {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new NotFoundException("User not found");
            } else {
                return user;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findByState method
     *
     * @param state The state of the users you are looking for
     * @return A list with found users
     * @throws DataException
     */
    @Override
    public List<User> findByState(UserState state) throws DataException {

        try {
            List<User> users = userRepository.findByState(state);
            if (users.isEmpty()) {
                throw new NotFoundException("Users not found");
            } else {
                return users;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

    /**
     * Implementation of the findByNickOrEmail method
     *
     * @param nick  The nick of the user you are looking for
     * @param email The email of the user you are looking for
     * @return A list with found users
     * @throws DataException
     */
    @Override
    public List<User> findByNickOrEmail(String nick, String email) throws DataException {
        try {
            List<User> users = userRepository.findByNickOrEmail(nick, email);
            if (users.isEmpty()) {
                throw new NotFoundException("Users not found");
            } else {
                return users;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

}
