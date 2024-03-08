package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for performing CRUD operations on User.
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
    User findByNick(String nick);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user
     * @return the user with the specified email address
     */
    User findByEmail(String email);

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
