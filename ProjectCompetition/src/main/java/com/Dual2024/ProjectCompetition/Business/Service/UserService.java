package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Utils.UserState;

import java.util.List;

/**
 * Interface that contains the methods of the user service.
 *
 * @author Francisco Balonero Olivera
 */
public interface UserService {
    /**
     * Gets all users.
     *
     * @return All users
     * @throws BusinessException
     */
    List<UserBO> getAllUsers() throws BusinessException;

    /**
     * Register user.
     *
     * @param userBO The user bo
     * @return The saved user bo
     * @throws BusinessException
     */
    UserBO registerUser(UserBO userBO) throws BusinessException;

    /**
     * Gets user by id.
     *
     * @param id The id
     * @return The user
     * @throws BusinessException
     */
    UserBO getUser(Long id) throws BusinessException;

    /**
     * Delete user.
     *
     * @param id The id
     * @throws BusinessException
     */
    void deleteUser(Long id) throws BusinessException;

    /**
     * Gets user by nick.
     *
     * @param nick The nick
     * @return The user by nick
     * @throws BusinessException
     */
    UserBO getUserByNick(String nick) throws BusinessException;

    /**
     * Gets user by email.
     *
     * @param email The email
     * @return The user by email
     * @throws BusinessException
     */
    UserBO getUserByEmail(String email) throws BusinessException;

    /**
     * Gets users by state.
     *
     * @param state The state
     * @return The users by state
     * @throws BusinessException
     */
    List<UserBO> getUsersByState(UserState state) throws BusinessException;

    /**
     * Update user .
     *
     * @param id       The id
     * @param avatar   The avatar
     * @param password The password
     * @return the updated user bo
     * @throws BusinessException
     */
    UserBO UpdateUser(Long id, String avatar, String password) throws BusinessException;

    /**
     * Update role of user (Only for admins)
     *
     * @param id    The id
     * @param roles The roles
     * @return The user bo
     * @throws BusinessException
     */
    UserBO UpdateRoleUser(Long id, List<RoleBO> roles) throws BusinessException;
}
