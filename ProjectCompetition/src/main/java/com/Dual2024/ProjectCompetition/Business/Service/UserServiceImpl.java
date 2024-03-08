package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.*;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the UserService interface.
 *
 * @author Francisco Balonero Olivera
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * The User dao.
     */
    @Autowired
    UserDAO userDAO;
    /**
     * The Role dao.
     */
    @Autowired
    RoleDAO roleDAO;
    /**
     * The Bo to model converter.
     */
    @Autowired
    BOToModelConverter boToModelConverter;

    /**
     * The Model to bo converter.
     */
    @Autowired
    ModelToBOConverter modelToBOConverter;

    @Override
    public List<UserBO> getAllUsers() throws BusinessException {
        List<UserBO> listUserBO = new ArrayList<UserBO>();
        try {
            userDAO.findAll().forEach((User user) -> listUserBO.add(modelToBOConverter.userModelToBO(user)));
            return listUserBO;
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("Users not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find users", null);
        }
    }

    @Override
    @Transactional
    public UserBO registerUser(UserBO userBO) throws BusinessException {
        try {
            UserBO found = modelToBOConverter
                    .userModelToBO(userDAO.findByNickOrEmail(userBO.getNick(), userBO.getEmail()).getFirst());
            if (found.getEmail().equals(userBO.getEmail())) {
                throw new DuplicatedEmailException("This email is already registered");
            }
            if (found.getNick().equals(userBO.getNick())) {
                throw new DuplicatedNickException("This email is already registered");
            }
        } catch (DataException ignored) {

        }
        try {
            List<RoleBO> defaultRole = new ArrayList<RoleBO>();
            defaultRole.add(modelToBOConverter.roleModelToBO(roleDAO.findByName("JUGADOR")));
            userBO.setRoles(defaultRole);
            return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(userBO)));

        } catch (DataException e) {
            throw new BusinessException("Error registering user", e);
        }
    }

    @Override
    public UserBO getUser(Long id) throws BusinessException {
        try {
            return modelToBOConverter.userModelToBO(userDAO.findById(id));
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find user ", null);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) throws BusinessException {

        try {
            UserBO userBO = modelToBOConverter.userModelToBO(userDAO.findById(id));
            if (userBO.isInActiveTournament()) {
                throw new UserInActiveTournamentException("This user is in an active tournament");
            } else {
                userDAO.delete(id);
            }
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            throw new BusinessException("User not deleted", e);
        }

    }

    @Override
    public UserBO getUserByNick(String nick) throws BusinessException {

        try {
            return modelToBOConverter.userModelToBO(userDAO.findByNick(nick));
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find user", null);
        }
    }

    @Override
    public UserBO getUserByEmail(String email) throws BusinessException {

        try {
            return modelToBOConverter.userModelToBO(userDAO.findByEmail(email));
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find user", null);
        }
    }

    @Override
    public List<UserBO> getUsersByState(UserState state) throws BusinessException {
        List<UserBO> listUserBO = new ArrayList<UserBO>();
        try {
            userDAO.findByState(state).forEach((User user) -> listUserBO.add(modelToBOConverter.userModelToBO(user)));
            return listUserBO;
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("Users not found", e);
        } catch (DataException e) {
            throw new BusinessException("Error when trying to find users", null);
        }

    }

    @Override
    @Transactional
    public UserBO UpdateUser(Long id, String avatar, String password) throws BusinessException {
        UserBO user;
        try {
            user = modelToBOConverter.userModelToBO(userDAO.findById(id));
            user.setAvatar(avatar);
            user.setPassword(password);
            return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(user)));
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("This user not exists", e);
        } catch (DataException e) {
            throw new BusinessException("User could not be updated", e);
        }

    }

    @Override
    @Transactional
    public UserBO UpdateRoleUser(Long id, List<RoleBO> roles) throws BusinessException {
        UserBO user;
        if (roles.isEmpty() || roles.contains(null)) {
            throw new BusinessException("Roles cannot be null");
        } else {
            try {
                user = modelToBOConverter.userModelToBO(userDAO.findById(id));
                user.setRoles(roles);
                return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(user)));
            } catch (EntityNotFoundException e) {
                throw new UserNotFoundException("This user not exists", e);
            } catch (DataException e) {
                throw new BusinessException("User could not be updated", e);
            }
        }

    }

}
