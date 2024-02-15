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

import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.DataAccess.DAO")
public class UserDAOTest {
	@Autowired
	private UserDAO userDao;

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

		User savedUser = null;
		try {
			savedUser = userDao.save(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		User foundUser = null;
		try {
			foundUser = userDao.findById(user.getId());
		} catch (DataException e) {

			e.printStackTrace();
		}

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenUserObject_whenSave_theReturnSavedUser() {

		User savedUser = null;
		try {
			savedUser = userDao.save(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> userDao.save(duplicatedEmailUser));
		assertThrows(DataException.class, () -> userDao.save(duplicatedNickUser));
		assertThrows(DataException.class, () -> userDao.save(invalidPasswordUser));
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenUsersList_whenFindAll_theReturnUsersList() {

		try {
			userDao.save(user);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			userDao.save(user2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<User> users = null;
		try {
			users = userDao.findAll();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(users).isNotNull();
		assertThat(users.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByEmail operation")
	public void givenUser_whenFindByEmail_theReturnUser() {

		User savedUser = null;
		try {
			savedUser = userDao.save(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		User foundUser = null;
		try {
			foundUser = userDao.findByEmail("test@email.com");
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("JUnit test for findByNick operation")
	public void givenUser_whenFindByNick_theReturnUser() {

		User savedUser = null;
		try {
			savedUser = userDao.save(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		User foundUser = null;
		try {
			foundUser = userDao.findByNick("test");
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(savedUser);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenUser_whenUpdate_theReturnUpdatedUser() {

		try {
			userDao.save(user);
		} catch (DataException e) {
			e.printStackTrace();
		}
		User updatedUser = new User();
		updatedUser.setId(user.getId());
		updatedUser.setEmail("updated@email.com");
		updatedUser.setNick("updated");
		updatedUser.setPassword("updatedPassword");
		updatedUser.setState(UserState.CONECTADO);

		User savedUpdatedUser = null;
		try {
			savedUpdatedUser = userDao.save(updatedUser);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(savedUpdatedUser).isNotNull();
		assertThat(savedUpdatedUser).isEqualTo(updatedUser);
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenUser_whenDelete_thenDeleteUser() {

		try {
			userDao.save(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			userDao.delete(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> userDao.findByEmail("test@email.com"));
	}

}
