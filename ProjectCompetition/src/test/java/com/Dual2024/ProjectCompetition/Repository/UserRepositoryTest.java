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

import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.UserRepository;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest(showSql = false)
@Order(1)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	private User user, user2, duplicatedNickUser, duplicatedEmailUser, invalidPasswordUser;

	@BeforeEach
	public void setup() {
		user = User.builder().email("test@email.com").nick("test").password("passwordTest").state(UserState.CONECTADO)
				.build();
		user2 = User.builder().email("test2@email.com").nick("test2").password("passwordTest2")
				.state(UserState.CONECTADO).build();
		duplicatedNickUser = User.builder().email("test2@email.com").nick("test").password("passwordTest2")
				.state(UserState.CONECTADO).build();
		duplicatedEmailUser = User.builder().email("test@email.com").nick("test2").password("passwordTest2")
				.state(UserState.CONECTADO).build();
		invalidPasswordUser = User.builder().email("test3@email.com").nick("test3").password("passw")
				.state(UserState.CONECTADO).build();
	}

	@Test
	@DisplayName("JUnit test for findById operation")
	public void givenId_whenFindById_theReturnUser() {

		User savedUser = userRepository.save(user);

		User foundUser = userRepository.findById(user.getId()).get();

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenUserObject_whenSave_theReturnSavedUser() {

		User savedUser = userRepository.save(user);

		try {

			userRepository.save(duplicatedEmailUser);
		} catch (DataIntegrityViolationException e) {
			assertThat(e).isNotNull();
		}
		try {

			userRepository.save(duplicatedNickUser);
		} catch (DataIntegrityViolationException e) {
			assertThat(e).isNotNull();
		}
		try {

			userRepository.save(invalidPasswordUser);
		} catch (ConstraintViolationException e) {
			assertThat(e).isNotNull();
		}

		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenUsersList_whenFindAll_theReturnUsersList() {

		userRepository.save(user);
		userRepository.save(user2);

		List<User> users = userRepository.findAll();

		assertThat(users).isNotNull();
		assertThat(users.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByEmail operation")
	public void givenUser_whenFindByEmail_theReturnUser() {

		User savedUser = userRepository.save(user);

		User foundUser = userRepository.findByEmail("test@email.com");

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("JUnit test for findByNick operation")
	public void givenUser_whenFindByNick_theReturnUser() {

		User savedUser = userRepository.save(user);

		User foundUser = userRepository.findByNick("test");

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
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
		assertThat(savedUpdatedUser).isEqualTo(updatedUser);
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
