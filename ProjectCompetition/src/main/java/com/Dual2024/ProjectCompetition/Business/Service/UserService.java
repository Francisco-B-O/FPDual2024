package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.List;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

public interface UserService {
	List<UserBO> getAllUsers() throws BusinessException;

	UserBO registerUser(UserBO userBO) throws BusinessException;

	UserBO getUser(Long id) throws BusinessException;

	void deleteUser(Long id) throws BusinessException;

	UserBO getUserByNick(String nick) throws BusinessException;

	UserBO getUserByEmail(String email) throws BusinessException;

	List<UserBO> getUsersByState(UserState state) throws BusinessException;

	UserBO UpdateUser(Long id, String avatar, String password) throws BusinessException;

	UserBO UpdateRoleUser(Long id, List<RoleBO> roles) throws BusinessException;
}
