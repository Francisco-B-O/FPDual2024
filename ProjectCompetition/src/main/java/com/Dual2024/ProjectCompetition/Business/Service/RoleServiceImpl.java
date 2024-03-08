package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;
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
