package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Dual2024.ProjectCompetition.Model.User;
import com.Dual2024.ProjectCompetition.Model.UserState;

@DataJpaTest(showSql = false)
@Order(1)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	private User user;

	@BeforeEach
	public void setup() {
		user = new User();
		user.setEmail("test@email.com");
		user.setNick("test");
		user.setPassword("passwordTest");
		user.setState(UserState.CONECTADO);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenUserObject_whenSave_theReturnSavedUser() {
		User savedUser = userRepository.save(user);
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenUsersList_whenFindAll_theReturnUsersList() {
		User user2 = new User();
		user2.setEmail("test2@email.com");
		user2.setNick("test2");
		user2.setPassword("passwordTest2");
		user2.setState(UserState.CONECTADO);
		userRepository.save(user);
		userRepository.save(user2);
		List<User> users = userRepository.findAll();
		assertThat(users).isNotNull();
		assertThat(users.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByEmail operation")
	public void givenUser_whenFindByEmail_theReturnUser() {
		userRepository.save(user);
		User foundUser = userRepository.findByEmail("test@email.com");
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getId()).isEqualTo(user.getId());
	}

	@Test
	@DisplayName("JUnit test for findByNick operation")
	public void givenUser_whenFindByNick_theReturnUser() {
		userRepository.save(user);
		User foundUser = userRepository.findByNick("test");
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getId()).isEqualTo(user.getId());
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenUser_whenUpdate_theReturnUpdatedUser() {
		userRepository.save(user);
		User updatedUser = new User();
		updatedUser.setId(user.getId());
		updatedUser.setEmail("updated@email.com");
		updatedUser.setNick("updated");
		updatedUser.setPassword("updatedPassword");
		updatedUser.setState(UserState.CONECTADO);
		User savedUpdatedUser = userRepository.save(updatedUser);
		assertThat(savedUpdatedUser).isNotNull();
		assertThat(savedUpdatedUser.getId()).isEqualTo(user.getId());
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenUser_whenDelete_thenDeleteUser() {
		userRepository.save(user);
		userRepository.delete(user);
		User deletedUser = userRepository.findByEmail("test@email.com");
		assertThat(deletedUser).isNull();
	}

}
