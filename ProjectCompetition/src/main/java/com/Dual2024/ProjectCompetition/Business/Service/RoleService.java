package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;

import java.util.List;

/**
 * Interface that contains the methods of the role service.
 *
 * @author Francisco Balonero Olivera
 */
public interface RoleService {
    /**
     * Gets all roles.
     *
     * @return All roles
     * @throws BusinessException
     */
    List<RoleBO> getAllRoles() throws BusinessException;
}
