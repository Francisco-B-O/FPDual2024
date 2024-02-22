package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	BOToModelConverter boToModelConverter;

	@Autowired
	ModelToBOConverter modelToBOConverter;

	@Override
	public List<RoleBO> getAllRoles() throws BusinessException {

		List<RoleBO> listRolesBO = new ArrayList<RoleBO>();
		try {
			roleDAO.findAll().forEach((Role role) -> listRolesBO.add(modelToBOConverter.roleModelToBO(role)));
			return listRolesBO;
		} catch (DataException e) {
			throw new BusinessException("Roles not found", e);
		}
	}

}
