package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.RoleRepository;

@DataJpaTest(showSql = false)
@Order(2)
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;
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

		Role savedRole = roleRepository.save(role);

		Role foundRole = roleRepository.findById(role.getId()).get();

		assertThat(foundRole).isNotNull();
		assertThat(foundRole).isEqualTo(savedRole);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenRole_whenSave_thenSaveRole() {

		Role savedRole = roleRepository.save(role);

		try {
			roleRepository.save(duplicatedNameRole);
		} catch (DataIntegrityViolationException e) {
			assertThat(e).isNotNull();
		}

		assertThat(savedRole).isNotNull();
		assertThat(savedRole).isEqualTo(role);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenRolesList_whenFindAll_thenReturnRolesList() {

		roleRepository.save(role);
		roleRepository.save(role2);

		List<Role> roles = roleRepository.findAll();

		assertThat(roles).isNotNull();
		assertThat(roles.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenRole_whenFindByName_thenReturnRole() {

		Role savedRole = roleRepository.save(role);

		Role foundRole = roleRepository.findByName("test");

		assertThat(foundRole).isNotNull();
		assertThat(foundRole).isEqualTo(savedRole);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenRole_whenUpdate_thenUpdateRole() {

		roleRepository.save(role);
		Role updatedRole = new Role();
		updatedRole.setId(role.getId());
		updatedRole.setName("updated");
		updatedRole.setDescription("Updated role");

		Role savedUpdatedRole = roleRepository.save(updatedRole);

		assertThat(savedUpdatedRole).isNotNull();
		assertThat(savedUpdatedRole).isEqualTo(updatedRole);
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenRole_whenDelete_thenDeleteRole() {

		roleRepository.save(role);

		roleRepository.delete(role);

		Role deletedRole = roleRepository.findByName("test");
		assertThat(deletedRole).isNull();
	}

}