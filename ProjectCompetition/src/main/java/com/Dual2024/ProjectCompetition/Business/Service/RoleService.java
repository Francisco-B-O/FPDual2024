package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.List;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;

public interface RoleService {
	List<RoleBO> getAllRoles() throws BusinessException;
}
