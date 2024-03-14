package com.dual2024.projectcompetition.dao;

import com.dual2024.projectcompetition.dataaccess.dao.RoleDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.dual2024.projectcompetition.dataAccess.dao")
public class RoleDAOTest {
    @Autowired
    private RoleDAO roleDAO;
    private Role role, role2, duplicatedNameRole;

    @BeforeEach
    public void setup() {
        role = Role.builder().name("test").description("Test role").build();
        role2 = Role.builder().name("test2").description("Test role").build();
        duplicatedNameRole = Role.builder().name("test").description("Test role").build();
    }

    @Test
    @DisplayName("findById operation")
    public void givenId_whenFindById_theReturnRole() throws DataException {

        Role savedRole = roleDAO.save(role);

        Role foundRole = roleDAO.findById(role.getId());

        assertThat(foundRole).isNotNull();
        assertThat(foundRole).isEqualTo(savedRole);
    }

    @Test
    @DisplayName("save operation")
    public void givenRole_whenSave_thenSaveRole() throws DataException {

        Role savedRole = roleDAO.save(role);

        assertThrows(DataException.class, () -> roleDAO.save(duplicatedNameRole));
        assertThat(savedRole).isNotNull();
        assertThat(savedRole).isEqualTo(role);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenRolesList_whenFindAll_thenReturnRolesList() throws DataException {

        roleDAO.save(role);
        roleDAO.save(role2);

        List<Role> roles = roleDAO.findAll();

        assertThat(roles).isNotNull();
        assertThat(roles.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByName operation")
    public void givenRole_whenFindByName_thenReturnRole() throws DataException {

        Role savedRole = roleDAO.save(role);

        Role foundRole = roleDAO.findByName("test");

        assertThat(foundRole).isNotNull();
        assertThat(foundRole).isEqualTo(savedRole);
    }

    @Test
    @DisplayName("update operation")
    public void givenRole_whenUpdate_thenUpdateRole() throws DataException {

        roleDAO.save(role);
        Role updatedRole = new Role();
        updatedRole.setId(role.getId());
        updatedRole.setName("updated");
        updatedRole.setDescription("Updated role");

        Role savedUpdatedRole = roleDAO.save(updatedRole);

        assertThat(savedUpdatedRole).isNotNull();
        assertThat(savedUpdatedRole).isEqualTo(updatedRole);
    }

    @Test
    @DisplayName("delete operation")
    public void givenRole_whenDelete_thenDeleteRole() throws DataException {

        roleDAO.save(role);

        roleDAO.delete(role.getId());

        assertThrows(DataException.class, () -> roleDAO.findByName("test"));
    }
}
