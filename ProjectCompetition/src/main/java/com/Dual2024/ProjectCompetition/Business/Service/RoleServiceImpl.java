package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the RoleService interface.
 *
 * @author Francisco Balonero Olivera * @see ModalityService
 * @see RoleService
 * @see RoleDAO
 * @see ModelToBOConverter
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    /**
     * The Role DAO.
     */
    @Autowired
    RoleDAO roleDAO;
    /**
     * The Model to BO converter.
     */
    @Autowired
    ModelToBOConverter modelToBOConverter;

    @Override
    public List<RoleBO> getAllRoles() throws BusinessException {
        List<RoleBO> listRolesBO = new ArrayList<>();
        try {
            roleDAO.findAll().forEach(role -> {
                RoleBO roleBO = modelToBOConverter.roleModelToBO(role);
                listRolesBO.add(roleBO);
            });
            log.info("Found {} roles", listRolesBO.size());
            return listRolesBO;
        } catch (DataException e) {
            log.error("Error finding all roles", e);
            throw new BusinessException("Roles not found", e);
        }
    }

}
