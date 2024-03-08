package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.UserRepository;
import com.Dual2024.ProjectCompetition.Utils.UserState;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest(showSql = false)
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
	@DisplayName("findById operation")
	public void givenId_whenFindById_theReturnUser() {

		User savedUser = userRepository.save(user);

		User foundUser = userRepository.findById(user.getId()).get();

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("save operation")
	public void givenUserObject_whenSave_theReturnSavedUser() {

		User savedUser = userRepository.save(user);

		assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(duplicatedEmailUser));
		assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(duplicatedNickUser));
		assertThrows(ConstraintViolationException.class, () -> userRepository.save(invalidPasswordUser));
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findAll operation")
	public void givenUsersList_whenFindAll_theReturnUsersList() {

		userRepository.save(user);
		userRepository.save(user2);

		List<User> users = userRepository.findAll();

		assertThat(users).isNotNull();
		assertThat(users.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("findByEmail operation")
	public void givenUser_whenFindByEmail_theReturnUser() {

		User savedUser = userRepository.save(user);

		User foundUser = userRepository.findByEmail("test@email.com");

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("findByNick operation")
	public void givenUser_whenFindByNick_theReturnUser() {

		User savedUser = userRepository.save(user);

		User foundUser = userRepository.findByNick("test");

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("findByNickOrEmail operation")
	public void givenUser_whenFindByEmailOrNick_theReturnUser() {

		User savedUser = userRepository.save(user);

		User foundUser = userRepository.findByNickOrEmail("test", "no").getFirst();

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("update operation")
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
	@DisplayName("delete operation")
	public void givenUser_whenDeleteById_thenDeleteUser() {

		userRepository.save(user);

		userRepository.deleteById(user.getId());

		User deletedUser = userRepository.findByEmail("test@email.com");
		assertThat(deletedUser).isNull();
	}

}
