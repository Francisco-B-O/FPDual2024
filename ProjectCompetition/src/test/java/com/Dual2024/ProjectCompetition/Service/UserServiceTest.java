package com.Dual2024.ProjectCompetition.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedEmailException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNickException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.InvalidSizePasswordException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Business.Service.UserServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	private UserBO userBO;
	private User user;
	@Mock
	UserDAO userDAO;
	@Mock
	ModelToBOConverter modelToBOConverter;
	@Mock
	BOToModelConverter boToModelConverter;
	@InjectMocks
	UserServiceImpl userService;

	@BeforeEach
	public void setup() {
		user = User.builder().email("test@email.com").id(1L).nick("test").password("passwordTest")
				.state(UserState.CONECTADO).build();
		userBO = UserBO.builder().email("test@email.com").id(1L).nick("test").password("passwordTest")
				.state(UserState.CONECTADO).build();

	}

	@Test
	@DisplayName("JUnit test for userRegister operation : correct case")
	public void givenUserBO_whenUserRegister_thenReturnUserBO() {

		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(userDAO.findByNick(userBO.getNick())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(userDAO.save(user)).willReturn(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		UserBO savedUser = null;
		try {
			savedUser = userService.registerUser(userBO);
		} catch (BusinessException e) {
			e.printStackTrace();
			assertThat(e).isNull();
		}

		assertThat(savedUser).isNotNull();
		assertThat(savedUser).isEqualTo(userBO);
	}

	@Test
	@DisplayName("JUnit test for userRegister operation : incorrect case -> Duplicated email")
	public void givenUserBO_whenUserRegister_thenThrowDuplicatedEmailException() {

		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willReturn(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DuplicatedEmailException.class, () -> userService.registerUser(userBO));
	}

	@Test
	@DisplayName("JUnit test for userRegister operation : incorrect case -> Duplicated nick")
	public void givenUserBO_whenUserRegister_thenThrowDuplicatedNickException() {

		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(userDAO.findByNick(userBO.getNick())).willReturn(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DuplicatedNickException.class, () -> userService.registerUser(userBO));
	}

	@Test
	@DisplayName("JUnit test for userRegister operation : incorrect case -> Invalid size password")
	public void givenUserBO_whenUserRegister_thenThrowInvalidSizePasswordException() {

		userBO.setPassword("p");
		user.setPassword("p");
		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(userDAO.findByNick(userBO.getNick())).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			BDDMockito.given(userDAO.save(user)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		assertThrows(InvalidSizePasswordException.class, () -> userService.registerUser(userBO));

	}

	@Test
	@DisplayName("JUnit test for getUser operation : correct case")
	public void givenId_whenGetUser_thenReturnUserBO() {

		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

		UserBO foundUser = null;
		try {
			foundUser = userService.getUser(1L);
		} catch (BusinessException e) {
			e.printStackTrace();
			assertThat(e).isNull();
		}

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(userBO);
	}

	@Test
	@DisplayName("JUnit test for getUser operation : incorrect case -> notFound")
	public void givenId_whenGetUser_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(BusinessException.class, () -> userService.getUser(1L));
	}

	@Test
	@DisplayName("JUnit test for deleteUser operation : correct case")
	public void givenUserBO_whenDeleteUser_thenDeleteUser() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
			e.printStackTrace();
		}
		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.willDoNothing().given(userDAO).delete(user);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			userService.deleteUser(userBO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		try {
			verify(userDAO, times(1)).delete(user);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("JUnit test for deleteUser operation : incorrect case -> notFound")
	public void givenUserBOThatNotExists_whenDeleteUser_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
			e.printStackTrace();
		}
		assertThrows(BusinessException.class, () -> userService.deleteUser(userBO));

	}

}