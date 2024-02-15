package com.Dual2024.ProjectCompetition.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.DataAccess.DAO")
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
	@DisplayName("JUnit test for findById operation")
	public void givenId_whenFindById_theReturnRole() {

		Role savedRole = null;
		try {
			savedRole = roleDAO.save(role);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Role foundRole = null;
		try {
			foundRole = roleDAO.findById(role.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundRole).isNotNull();
		assertThat(foundRole).isEqualTo(savedRole);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenRole_whenSave_thenSaveRole() {

		Role savedRole = null;
		try {
			savedRole = roleDAO.save(role);
		} catch (DataException e) {

			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> roleDAO.save(duplicatedNameRole));
		assertThat(savedRole).isNotNull();
		assertThat(savedRole).isEqualTo(role);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenRolesList_whenFindAll_thenReturnRolesList() {

		try {
			roleDAO.save(role);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			roleDAO.save(role2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Role> roles = null;
		try {
			roles = roleDAO.findAll();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(roles).isNotNull();
		assertThat(roles.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenRole_whenFindByName_thenReturnRole() {

		Role savedRole = null;
		try {
			savedRole = roleDAO.save(role);
		} catch (DataException e) {

			e.printStackTrace();
		}

		Role foundRole = null;
		try {
			foundRole = roleDAO.findByName("test");
		} catch (DataException e) {

			e.printStackTrace();
		}

		assertThat(foundRole).isNotNull();
		assertThat(foundRole).isEqualTo(savedRole);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenRole_whenUpdate_thenUpdateRole() {

		try {
			roleDAO.save(role);
		} catch (DataException e) {
			e.printStackTrace();
		}
		Role updatedRole = new Role();
		updatedRole.setId(role.getId());
		updatedRole.setName("updated");
		updatedRole.setDescription("Updated role");

		Role savedUpdatedRole = null;
		try {
			savedUpdatedRole = roleDAO.save(updatedRole);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(savedUpdatedRole).isNotNull();
		assertThat(savedUpdatedRole).isEqualTo(updatedRole);
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenRole_whenDelete_thenDeleteRole() {

		try {
			roleDAO.save(role);
		} catch (DataException e) {

			e.printStackTrace();
		}

		try {
			roleDAO.delete(role);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> roleDAO.findByName("test"));
	}
}
