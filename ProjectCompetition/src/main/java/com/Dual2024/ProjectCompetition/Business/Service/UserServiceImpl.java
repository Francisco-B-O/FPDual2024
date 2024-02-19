package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedEmailException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNickException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.InvalidSizePasswordException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserInActiveTournamentException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentStateBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Autowired
	BOToModelConverter boToModelConverter;

	@Autowired
	ModelToBOConverter modelToBOConverter;

	@Override
	public List<UserBO> getAllUsers() throws BusinessException {
		List<UserBO> listUserBO = new ArrayList<UserBO>();
		try {
			userDAO.findAll().forEach((User user) -> listUserBO.add(modelToBOConverter.userModelToBO(user)));
			return listUserBO;
		} catch (DataException e) {
			throw new BusinessException("Users not found", e);
		}
	}

	@Override
	public UserBO registerUser(UserBO userBO) throws BusinessException {
		try {
			userDAO.findByEmail(userBO.getEmail());
			throw new DuplicatedEmailException("This email is already registered");
		} catch (DataException e) {
		}
		try {
			userDAO.findByNick(userBO.getNick());
			throw new DuplicatedNickException("This nickname is already registered");
		} catch (DataException e) {
		}
		try {

			return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(userBO)));

		} catch (DataException e) {
			if (userBO.getPassword().length() < 6) {
				throw new InvalidSizePasswordException("The password must be at least 6 characters", e);
			} else {
				throw new BusinessException("Error registering user", e);
			}
		}
	}

	@Override
	public UserBO getUser(Long id) throws BusinessException {
		try {
			return modelToBOConverter.userModelToBO(userDAO.findById(id));
		} catch (DataException e) {
			throw new BusinessException("User not found", e);
		}
	}

	@Override
	public void deleteUser(UserBO userBO) throws BusinessException {
		boolean isInActiveTournament = false;
		for (TeamBOAux team : userBO.getTeams()) {
			for (TournamentBOAux tournament : team.getTournaments()) {
				if (tournament.getState().equals(TournamentStateBO.EN_JUEGO)) {
					isInActiveTournament = true;
				}
			}
		}
		if (isInActiveTournament) {
			throw new UserInActiveTournamentException("This user is in an active tournament");
		} else {
			try {
				modelToBOConverter.userModelToBO(userDAO.findById(userBO.getId()));
				userDAO.delete(boToModelConverter.userBOToModel(userBO));
			} catch (DataException e) {
				throw new BusinessException("User not deleted", e);
			}
		}

	}

	@Override
	public UserBO getUserByNick(String nick) throws BusinessException {

		try {
			return modelToBOConverter.userModelToBO(userDAO.findByNick(nick));
		} catch (DataException e) {
			throw new BusinessException("User not found", e);
		}
	}

	@Override
	public UserBO getUserByEmail(String email) throws BusinessException {

		try {
			return modelToBOConverter.userModelToBO(userDAO.findByEmail(email));
		} catch (DataException e) {
			throw new BusinessException("User not found", e);
		}
	}

	@Override
	public List<UserBO> getUsersByState(UserState state) throws BusinessException {
		List<UserBO> listUserBO = new ArrayList<UserBO>();
		try {
			userDAO.findByState(state).forEach((User user) -> listUserBO.add(modelToBOConverter.userModelToBO(user)));
			return listUserBO;
		} catch (DataException e) {
			throw new BusinessException("Users not found", e);
		}

	}

	@Override
	public UserBO UpdateUser(Long id, String avatar) throws BusinessException {
		UserBO user = null;
		try {
			user = modelToBOConverter.userModelToBO(userDAO.findById(id));
			user.setAvatar(avatar);
		} catch (DataException e) {
			throw new BusinessException("This user not exists", e);
		}
		try {

			return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(user)));
		} catch (DataException e) {
			throw new BusinessException("User could not be updated", e);
		}

	}

	@Override
	public UserBO UpdateRoleUser(Long id, List<RoleBO> roles) throws BusinessException {
		UserBO user = null;
		if (roles.isEmpty() || roles.equals(null) || roles.contains(null)) {
			throw new BusinessException("Roles cannot be null");
		} else {
			try {
				user = modelToBOConverter.userModelToBO(userDAO.findById(id));
				user.setRoles(roles);
			} catch (DataException e) {
				throw new BusinessException("This user not exists", e);
			}
			try {

				return modelToBOConverter.userModelToBO(userDAO.save(boToModelConverter.userBOToModel(user)));
			} catch (DataException e) {
				throw new BusinessException("User could not be updated", e);
			}
		}

	}

}
