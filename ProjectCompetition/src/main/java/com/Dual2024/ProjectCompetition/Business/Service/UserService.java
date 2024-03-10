package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Utils.UserState;

import java.util.List;

/**
 * Service interface containing methods for managing users.
 *
 * <p>This interface declares methods for retrieving, registering, updating, and deleting users.
 * It also includes methods for retrieving users by various criteria, such as ID, nickname, email, and state.
 * Additionally, there are methods for updating user information, including avatar and password, and updating user roles.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Obtain an instance of UserService
 * UserService userService = // instantiate or inject the implementation
 *
 * // Retrieve all users
 * List<UserBO> allUsers = userService.getAllUsers();
 *
 * // Register a new user
 * UserBO newUser = // create a new user instance
 * UserBO registeredUser = userService.registerUser(newUser);
 *
 * // Retrieve a user by ID
 * Long userId = // obtain the user's ID
 * UserBO retrievedUser = userService.getUser(userId);
 *
 * // Delete a user
 * Long userToDeleteId = // obtain the ID of the user to delete
 * userService.deleteUser(userToDeleteId);
 *
 * // Retrieve a user by nickname
 * String userNick = // obtain the user nickname
 * UserBO userByNick = userService.getUserByNick(userNick);
 *
 * // Retrieve a user by email
 * String userEmail = // obtain the user email
 * UserBO userByEmail = userService.getUserByEmail(userEmail);
 *
 * // Retrieve users by state
 * UserState userState = // obtain the user state
 * List<UserBO> usersByState = userService.getUsersByState(userState);
 *
 * // Update user information (avatar and password)
 * Long userToUpdateId = // obtain the ID of the user to update
 * String newAvatar = // obtain the new avatar
 * String newPassword = // obtain the new password
 * UserBO updatedUser = userService.updateUser(userToUpdateId, newAvatar, newPassword);
 *
 * // Update user roles (only for admins)
 * Long userToUpdateRolesId = // obtain the ID of the user to update roles
 * List<RoleBO> newRoles = // obtain the new roles
 * UserBO updatedUserRole = userService.updateRoleUser(userToUpdateRolesId, newRoles);
 * }
 * </pre>
 *
 * <p>Implementing classes should handle {@link BusinessException} to appropriately manage exceptions
 * related to business operations on users.</p>
 *
 * <p>Instances of this interface are typically used in the business layer of the application to provide
 * a clean separation between the business logic and data access logic.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface UserService {
    /**
     * Retrieves all users.
     *
     * @return A list of all users
     * @throws BusinessException If an error occurs during the operation
     */
    List<UserBO> getAllUsers() throws BusinessException;

    /**
     * Registers a new user.
     *
     * @param userBO The user BO to register
     * @return The registered user BO
     * @throws BusinessException If an error occurs during the operation
     */
    UserBO registerUser(UserBO userBO) throws BusinessException;

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve
     * @return The user BO with the specified ID
     * @throws BusinessException If an error occurs during the operation
     */
    UserBO getUser(Long id) throws BusinessException;

    /**
     * Deletes a user.
     *
     * @param id The ID of the user to delete
     * @throws BusinessException If an error occurs during the operation
     */
    void deleteUser(Long id) throws BusinessException;

    /**
     * Retrieves a user by nickname.
     *
     * @param nick The nickname of the user to retrieve
     * @return The user BO with the specified nickname
     * @throws BusinessException If an error occurs during the operation
     */
    UserBO getUserByNick(String nick) throws BusinessException;

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user to retrieve
     * @return The user BO with the specified email
     * @throws BusinessException If an error occurs during the operation
     */
    UserBO getUserByEmail(String email) throws BusinessException;

    /**
     * Retrieves users by state.
     *
     * @param state The state of the users to retrieve
     * @return A list of users with the specified state
     * @throws BusinessException If an error occurs during the operation
     */
    List<UserBO> getUsersByState(UserState state) throws BusinessException;

    /**
     * Updates a user's information.
     *
     * @param id       The ID of the user to update
     * @param avatar   The new avatar of the user
     * @param password The new password of the user
     * @return The updated user BO
     * @throws BusinessException If an error occurs during the operation
     */
    UserBO updateUser(Long id, String avatar, String password) throws BusinessException;

    /**
     * Updates the role of a user (only for admins).
     *
     * @param id    The ID of the user to update roles
     * @param roles The new roles for the user
     * @return The updated user BO
     * @throws BusinessException If an error occurs during the operation
     */
    UserBO updateRoleUser(Long id, List<RoleBO> roles) throws BusinessException;
}
