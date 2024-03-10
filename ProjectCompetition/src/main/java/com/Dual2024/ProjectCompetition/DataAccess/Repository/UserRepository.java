package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for performing CRUD operations on User.
 *
 * <p>This interface extends the JpaRepository interface, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on User entities. The methods declared in this interface are automatically implemented by Spring Data JPA.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Data access operation to find a user by their nickname
 * Optional<User> userByNick = userRepository.findByNick("userNick");
 *
 * // Data access operation to find a user by their email address
 * Optional<User> userByEmail = userRepository.findByEmail("user@example.com");
 *
 * // Data access operation to find all users with the specified state
 * List<User> usersByState = userRepository.findByState(UserState.CONECTADO);
 *
 * // Data access operation to find all users that match the specified nickname or email address
 * List<User> usersByNickOrEmail = userRepository.findByNickOrEmail("userNick", "user@example.com");
 * }
 * </pre>
 *
 * <p>The interface includes methods to find a user by nickname, find a user by email, find all users with a specified state,
 * and find all users that match a specified nickname or email address.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their nickname.
     *
     * @param nick the nickname of the user
     * @return the user with the specified nickname
     */
    Optional<User> findByNick(String nick);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user
     * @return the user with the specified email address
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds all users with the specified state.
     *
     * @param state the state of the user
     * @return a list of users with the specified state
     */
    List<User> findByState(UserState state);

    /**
     * Finds all users that match the specified nickname or email address.
     *
     * @param nick  the nickname or email address of the user
     * @param email the email address of the user
     * @return a list of users that match the specified nickname or email address
     */
    List<User> findByNickOrEmail(String nick, String email);
}
