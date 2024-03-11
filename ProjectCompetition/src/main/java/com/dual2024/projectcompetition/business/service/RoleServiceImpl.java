package com.dual2024.projectcompetition.business.service;

import com.dual2024.projectcompetition.dataaccess.dao.RoleDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessobject.RoleBO;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
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
