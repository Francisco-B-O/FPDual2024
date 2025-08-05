package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.User;
import com.dual2024.projectcompetition.dataaccess.repository.UserRepository;
import com.dual2024.projectcompetition.utils.UserState;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 * @see UserDAO
 */
@Slf4j
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) throws DataException {
        try {
            User savedUser = userRepository.save(user);
            log.debug("User saved successfully: {}", savedUser.getId());
            log.info("User saved successfully");
            return savedUser;
        } catch (ConstraintViolationException | NestedRuntimeException e) {
            log.error("Error saving user", e);
            throw new DataException("User not saved", e);
        }
    }

    @Override
    public User findById(Long id) throws DataException {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            log.debug("User retrieved successfully: {}", id);
            log.info("User retrieved successfully");
            return user;
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find user: {}", id, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<User> findAll() throws DataException {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                log.warn("No users found");
                throw new EntityNotFoundException("Users not found");
            } else {
                log.info("Found {} users", users.size());
                return users;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find all users", nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public void delete(Long id) throws DataException {
        try {
            userRepository.deleteById(id);
            log.debug("User deleted successfully: {}", id);
            log.info("User deleted successfully");
        } catch (NestedRuntimeException nre) {
            log.error("Error deleting user: {}", id, nre);
            throw new DataException("User not deleted", nre);
        }
    }

    @Override
    public User findByNick(String nick) throws DataException {
        try {
            User user = userRepository.findByNick(nick)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            log.debug("User retrieved successfully by nick: {}", nick);
            log.info("User retrieved successfully by nick");
            return user;
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find user by nick: {}", nick, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public User findByEmail(String email) throws DataException {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            log.debug("User retrieved successfully by email: {}", email);
            log.info("User retrieved successfully by email");
            return user;
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find user by email: {}", email, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<User> findByState(UserState state) throws DataException {
        try {
            List<User> users = userRepository.findByState(state);
            if (users.isEmpty()) {
                log.warn("No users found with state: {}", state);
                throw new EntityNotFoundException("Users not found");
            } else {
                log.info("Found {} users with state: {}", users.size(), state);
                return users;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find users by state: {}", state, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<User> findByNickOrEmail(String nick, String email) throws DataException {
        try {
            List<User> users = userRepository.findByNickOrEmail(nick, email);
            if (users.isEmpty()) {
                log.warn("No users found with nick or email: {}, {}", nick, email);
                throw new EntityNotFoundException("Users not found");
            } else {
                log.info("Found {} users with nick or email", users.size());
                return users;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error when trying to find users by nick or email: {}, {}", nick, email, nre);
            throw new DataException("Data access error", nre);
        }
    }

}
