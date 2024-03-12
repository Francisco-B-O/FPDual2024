package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessobject.RoleBO;
import com.dual2024.projectcompetition.business.service.RoleService;
import com.dual2024.projectcompetition.presentation.dto.RoleDTO;
import com.dual2024.projectcompetition.presentation.dto.converters.BOToDTOConverter;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * Controller class for managing roles.
 *
 * <p>This class defines a RESTful endpoint for retrieving all roles in the system. Only users with
 * the 'ROLE_ADMIN' role are authorized to access this endpoint.
 *
 * @author Franciosco Balonero Olivera
 * @see RoleService
 * @see BOToDTOConverter
 */
@RequestMapping("role")
@RestController
@Tag(name = "Role", description = "Operations related to roles management")
public class RoleController {
    @Autowired
    private BOToDTOConverter boToDTOConverter;

    @Autowired
    private RoleService roleService;

    /**
     * Retrieves all roles in the system.
     *
     * @return List of all roles
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get all roles")
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
