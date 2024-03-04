package com.Dual2024.ProjectCompetition.Presentation.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.Service.RoleService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RoleDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;

@RequestMapping("role")
@RestController
public class RoleController {
	@Autowired
	BOToDTOConverter boToDTOConverter;
	@Autowired
	DTOToBOConverter dtoToBOConverter;
	@Autowired
	private RoleService roleService;

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
