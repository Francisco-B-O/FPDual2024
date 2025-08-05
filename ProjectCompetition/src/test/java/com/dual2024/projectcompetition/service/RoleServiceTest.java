package com.dual2024.projectcompetition.service;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessobject.RoleBO;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import com.dual2024.projectcompetition.business.service.RoleServiceImpl;
import com.dual2024.projectcompetition.dataaccess.dao.RoleDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        roleBO = RoleBO.builder().id(1L).name("PLAYER").description("PLAYER role").build();
        role = Role.builder().id(1L).name("PLAYER").description("PLAYER role").build();
    }

    @Test
    @DisplayName("getAllRoles operation : correct case")
    public void givenNothing_whenGetAllRoles_thenReturnAllRoles() throws DataException, BusinessException {

        List<Role> rolesList = new ArrayList<Role>();
        rolesList.add(role);
        BDDMockito.given(modelToBOConverter.roleModelToBO(role)).willReturn(roleBO);
        BDDMockito.given(roleDAO.findAll()).willReturn(rolesList);

        List<RoleBO> roles = roleService.getAllRoles();

        assertThat(roles).isNotNull();
        assertThat(roles).isNotEmpty();
        assertThat(roles.getFirst()).isEqualTo(roleBO);
    }
}
