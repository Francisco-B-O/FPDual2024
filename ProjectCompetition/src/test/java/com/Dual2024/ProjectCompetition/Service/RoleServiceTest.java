package com.Dual2024.ProjectCompetition.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.Service.RoleServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
	private RoleBO roleBO;
	private Role role;
	@Mock
	RoleDAO roleDAO;
	@Mock
	ModelToBOConverter modelToBOConverter;
	@InjectMocks
	RoleServiceImpl roleService;

	@BeforeEach
	public void setup() {
		roleBO = RoleBO.builder().id(1L).name("Jugador").description("Rol de jugador").build();
		role = Role.builder().id(1L).name("Jugador").description("Rol de jugador").build();
	}

	@Test
	@DisplayName("getAllRoles operation : correct case")
	public void givenNothing_whenGetAllRoles_thenReturnAllRoles() {

		List<Role> rolesList = new ArrayList<Role>();
		rolesList.add(role);
		BDDMockito.given(modelToBOConverter.roleModelToBO(role)).willReturn(roleBO);
		try {
			BDDMockito.given(roleDAO.findAll()).willReturn(rolesList);
		} catch (DataException e) {
		}

		List<RoleBO> roles = null;
		try {
			roles = roleService.getAllRoles();
		} catch (BusinessException e) {
		}

		assertThat(roles).isNotNull();
		assertThat(roles).isNotEmpty();
		assertThat(roles.getFirst()).isEqualTo(roleBO);
	}
}
