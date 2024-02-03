package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Dual2024.ProjectCompetition.Model.Role;

@DataJpaTest(showSql = false)
@Order(2)
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;
	private Role role;

	@BeforeEach
	public void setup() {
		role = new Role();
		role.setName("test");
		role.setDescription("Test role");
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenRole_whenSave_thenSaveRole() {
		Role savedRole = roleRepository.save(role);
		assertThat(savedRole).isNotNull();
		assertThat(savedRole.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenRolesList_whenFindAll_thenReturnRolesList() {
		Role role1 = new Role();
		role1.setName("Role 1");
		role1.setDescription("Description of Role 1");

		Role role2 = new Role();
		role2.setName("Role 2");
		role2.setDescription("Description of Role 2");

		Role role3 = new Role();
		role3.setName("Role 3");
		role3.setDescription("Description of Role 3");
		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role3);
		List<Role> roles = roleRepository.findAll();

		assertThat(roles).isNotNull();
		assertThat(roles.size()).isGreaterThan(0);
		assertThat(roles.contains(role1)).isTrue();
		assertThat(roles.contains(role2)).isTrue();
		assertThat(roles.contains(role3)).isTrue();
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenRole_whenFindByName_thenReturnRole() {
		roleRepository.save(role);
		Role foundRole = roleRepository.findByName("test");
		assertThat(foundRole).isNotNull();
		assertThat(foundRole.getId()).isEqualTo(role.getId());
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenRole_whenUpdate_thenUpdateRole() {
		roleRepository.save(role);
		Role updatedRole = new Role();
		updatedRole.setId(role.getId());
		updatedRole.setName("updated");
		updatedRole.setDescription("Updated role");
		roleRepository.save(updatedRole);
		Role foundRole = roleRepository.findByName("updated");
		assertThat(foundRole).isNotNull();
		assertThat(foundRole.getId()).isEqualTo(role.getId());
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