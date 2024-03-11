package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessobject.RoleBO;
import com.dual2024.projectcompetition.business.service.RoleService;
import com.dual2024.projectcompetition.presentation.dto.RoleDTO;
import com.dual2024.projectcompetition.presentation.dto.converters.BOToDTOConverter;
import com.dual2024.projectcompetition.presentation.dto.converters.DTOToBOConverter;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
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
