package com.dual2024.projectcompetition.business.service;

import com.dual2024.projectcompetition.dataaccess.dao.RoleDAO;
import com.dual2024.projectcompetition.dataaccess.dao.UserDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.User;
import com.dual2024.projectcompetition.utils.UserState;
import com.dual2024.projectcompetition.business.businessexception.*;
import com.dual2024.projectcompetition.business.businessobject.RoleBO;
import com.dual2024.projectcompetition.business.businessobject.UserBO;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the UserService interface.
 *
 * @author Francisco Balonero Olivera
 * @see UserService
 * @see RoleDAO
 * @see UserDAO
 * @see BOToModelConverter
 * @see ModelToBOConverter
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    /**
     * The User DAO.
     */
    @Autowired
    UserDAO userDAO;
    /**
     * The Role DAO.
     */
    @Autowired
    RoleDAO roleDAO;
    /**
     * The BO to Model converter.
     */
    @Autowired
    BOToModelConverter boToModelConverter;

    /**
     * The Model to BO converter.
     */
    @Autowired
    ModelToBOConverter modelToBOConverter;

    @Override
    public List<UserBO> getAllUsers() throws BusinessException {
        List<UserBO> listUserBO = new ArrayList<>();
        try {
            userDAO.findAll().forEach((User user) -> listUserBO.add(modelToBOConverter.userModelToBO(user)));
            return listUserBO;
        } catch (EntityNotFoundException e) {
            log.error("Users not found", e);
            throw new UserNotFoundException("Users not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find users", e);
            throw new BusinessException("Error when trying to find users", e);
        }
    }

    @Override
    @Transactional
    public UserBO registerUser(UserBO userBO) throws BusinessException {
        try {
            UserBO found = modelToBOConverter
                    .userModelToBO(userDAO.findByNickOrEmail(userBO.getNick(), userBO.getEmail()).getFirst());
            if (found.getEmail().equals(userBO.getEmail())) {
                log.error("This email is already registered {}", userBO.getEmail());
                throw new DuplicatedEmailException("This email is already registered");
            }
            if (found.getNick().equals(userBO.getNick())) {
                log.error("This nick is already registered: {}", userBO.getNick());
                throw new DuplicatedNickException("This nick is already registered");
            }
        } catch (DataException ignored) {

        }
        try {
            List<RoleBO> defaultRole = new ArrayList<>();
            defaultRole.add(modelToBOConverter.roleModelToBO(roleDAO.findByName("JUGADOR")));
            userBO.setRoles(defaultRole);
            return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(userBO)));
        } catch (DataException e) {
            log.error("Error registering user", e);
            throw new BusinessException("Error registering user", e);
        }
    }

    @Override
    public UserBO getUser(Long id) throws BusinessException {
        try {
            return modelToBOConverter.userModelToBO(userDAO.findById(id));
        } catch (EntityNotFoundException e) {
            log.error("User not found", e);
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find user", e);
            throw new BusinessException("Error when trying to find user", e);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) throws BusinessException {
        try {
            UserBO userBO = modelToBOConverter.userModelToBO(userDAO.findById(id));
            if (userBO.isInActiveTournament()) {
                log.error("This user is in an active tournament");
                throw new UserInActiveTournamentException("This user is in an active tournament");
            } else {
                userDAO.delete(id);
            }
        } catch (EntityNotFoundException e) {
            log.error("User not found", e);
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            log.error("User not deleted", e);
            throw new BusinessException("User not deleted", e);
        }
    }

    @Override
    public UserBO getUserByNick(String nick) throws BusinessException {
        try {
            return modelToBOConverter.userModelToBO(userDAO.findByNick(nick));
        } catch (EntityNotFoundException e) {
            log.error("User not found", e);
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find user", e);
            throw new BusinessException("Error when trying to find user", e);
        }
    }

    @Override
    public UserBO getUserByEmail(String email) throws BusinessException {
        try {
            return modelToBOConverter.userModelToBO(userDAO.findByEmail(email));
        } catch (EntityNotFoundException e) {
            log.error("User not found", e);
            throw new UserNotFoundException("User not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find user", e);
            throw new BusinessException("Error when trying to find user", e);
        }
    }

    @Override
    public List<UserBO> getUsersByState(UserState state) throws BusinessException {
        List<UserBO> listUserBO = new ArrayList<>();
        try {
            userDAO.findByState(state).forEach((User user) -> listUserBO.add(modelToBOConverter.userModelToBO(user)));
            return listUserBO;
        } catch (EntityNotFoundException e) {
            log.error("Users not found", e);
            throw new UserNotFoundException("Users not found", e);
        } catch (DataException e) {
            log.error("Error when trying to find users", e);
            throw new BusinessException("Error when trying to find users", e);
        }
    }

    @Override
    @Transactional
    public UserBO updateUser(Long id, String avatar, String password) throws BusinessException {
        UserBO user;
        try {
            user = modelToBOConverter.userModelToBO(userDAO.findById(id));
            user.setAvatar(avatar);
            user.setPassword(password);
            return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(user)));
        } catch (EntityNotFoundException e) {
            log.error("This user not exists", e);
            throw new UserNotFoundException("This user not exists", e);
        } catch (DataException e) {
            log.error("User could not be updated", e);
            throw new BusinessException("User could not be updated", e);
        }
    }

    @Override
    @Transactional
    public UserBO updateRoleUser(Long id, List<RoleBO> roles) throws BusinessException {
        UserBO user;
        if (roles.isEmpty() || roles.contains(null)) {
            throw new BusinessException("Roles cannot be null");
        } else {
            try {
                user = modelToBOConverter.userModelToBO(userDAO.findById(id));
                user.setRoles(roles);
                return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(user)));
            } catch (EntityNotFoundException e) {
                log.error("This user not exists", e);
                throw new UserNotFoundException("This user not exists", e);
            } catch (DataException e) {
                log.error("User could not be updated", e);
                throw new BusinessException("User could not be updated", e);
            }
        }
    }

}
