package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
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

    @Override
    public User save(User user) throws DataException {
        try {
            return userRepository.save(user);
        } catch (ConstraintViolationException | NestedRuntimeException e) {
            throw new DataException("User not saved", e);
        }
    }


    @Override
    public User findById(Long id) throws DataException {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new EntityNotFoundException("User not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }


    @Override
    public List<User> findAll() throws DataException {
        try {

            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new EntityNotFoundException("Users not found");
            } else {
                return users;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }


    @Override
    public void delete(Long id) throws DataException {
        try {
            userRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("User not deleted", nre);
        }

    }


    @Override
    public User findByNick(String nick) throws DataException {
        try {
            User user = userRepository.findByNick(nick);
            if (user == null) {
                throw new EntityNotFoundException("User not found");
            } else {
                return user;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }


    @Override
    public User findByEmail(String email) throws DataException {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new EntityNotFoundException("User not found");
            } else {
                return user;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }


    @Override
    public List<User> findByState(UserState state) throws DataException {

        try {
            List<User> users = userRepository.findByState(state);
            if (users.isEmpty()) {
                throw new EntityNotFoundException("Users not found");
            } else {
                return users;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }


    @Override
    public List<User> findByNickOrEmail(String nick, String email) throws DataException {
        try {
            List<User> users = userRepository.findByNickOrEmail(nick, email);
            if (users.isEmpty()) {
                throw new EntityNotFoundException("Users not found");
            } else {
                return users;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

}
