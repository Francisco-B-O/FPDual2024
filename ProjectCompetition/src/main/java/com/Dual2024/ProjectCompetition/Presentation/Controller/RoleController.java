package com.Dual2024.ProjectCompetition.Presentation.Controller;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.Service.RoleService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.Converters.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.Converters.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RoleDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * The Role controller.
 *
 * @author Franciosco Balonero Olivera
 */
@RequestMapping("role")
@RestController
public class RoleController {
    @Autowired
    private BOToDTOConverter boToDTOConverter;
    @Autowired
    private DTOToBOConverter dtoToBOConverter;
    @Autowired
    private RoleService roleService;

    /**
     * Gets all roles.
     *
     * @return the all roles
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<RoleDTO> getAllRoles() throws PresentationException {
        List<RoleDTO> listRoleDTO = new ArrayList<RoleDTO>();
        try {
            roleService.getAllRoles().forEach((RoleBO role) -> listRoleDTO.add(boToDTOConverter.roleBOToDTO(role)));
            return listRoleDTO;
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

}
