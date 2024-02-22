package com.Dual2024.ProjectCompetition.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedEmailException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNickException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserInActiveTournamentException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Business.Service.UserServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	private UserBO userBO;
	private User user;
	private RoleBO roleBO;
	private Role role;
	private List<UserBO> usersBOList;
	private List<User> usersList;
	@Mock
	UserDAO userDAO;
	@Mock
	RoleDAO roleDAO;
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
		Tournament tournament = Tournament.builder().state(TournamentState.NO_COMENZADO).build();
		List<Tournament> tournaments = new ArrayList<Tournament>();
		tournaments.add(tournament);
		Team team = Team.builder().tournaments(tournaments).build();
		List<Team> teams = new ArrayList<Team>();
		teams.add(team);
		user.setTeams(teams);
		TournamentBOAux tournamentBOAUX = TournamentBOAux.builder().state(TournamentState.NO_COMENZADO).build();
		List<TournamentBOAux> tournamentsBO = new ArrayList<TournamentBOAux>();
		tournamentsBO.add(tournamentBOAUX);
		TeamBOAux teamBO = TeamBOAux.builder().tournaments(tournamentsBO).build();
		List<TeamBOAux> teamsBO = new ArrayList<TeamBOAux>();
		teamsBO.add(teamBO);
		userBO.setTeams(teamsBO);
		usersBOList = new ArrayList<UserBO>();
		usersBOList.add(userBO);
		usersList = new ArrayList<User>();
		usersList.add(user);
		roleBO = RoleBO.builder().id(1L).name("JUGADOR").description("Rol de jugador").build();
		role = Role.builder().id(1L).name("JUGADOR").description("Rol de jugador").build();
	}

	@Test
	@DisplayName("userRegister operation : correct case")
	public void givenUserBO_whenUserRegister_thenReturnUserBO() {

		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		BDDMockito.given(modelToBOConverter.roleModelToBO(role)).willReturn(roleBO);
		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willThrow(DataException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.findByNick(userBO.getNick())).willThrow(DataException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(roleDAO.findByName("JUGADOR")).willReturn(role);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.save(user)).willReturn(user);
		} catch (DataException e) {
		}

		UserBO savedUser = null;
		try {
			savedUser = userService.registerUser(userBO);
		} catch (BusinessException e) {
		}

		assertThat(savedUser).isNotNull();
		assertThat(savedUser).isEqualTo(userBO);
	}

	@Test
	@DisplayName("userRegister operation : incorrect case -> Duplicated email")
	public void givenUserBO_whenUserRegister_thenThrowDuplicatedEmailException() {

		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willReturn(user);
		} catch (DataException e) {
		}

		assertThrows(DuplicatedEmailException.class, () -> userService.registerUser(userBO));
	}

	@Test
	@DisplayName("userRegister operation : incorrect case -> Duplicated nick")
	public void givenUserBO_whenUserRegister_thenThrowDuplicatedNickException() {

		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willThrow(DataException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.findByNick(userBO.getNick())).willReturn(user);
		} catch (DataException e) {
		}

		assertThrows(DuplicatedNickException.class, () -> userService.registerUser(userBO));
	}

	@Test
	@DisplayName("userRegister operation : incorrect case -> Constraint violation (password in this case)")
	public void givenUserBO_whenUserRegister_thenThrowBusinessException() {

		userBO.setPassword("p");
		user.setPassword("p");
		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		try {
			BDDMockito.given(userDAO.findByEmail(userBO.getEmail())).willThrow(DataException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.findByNick(userBO.getNick())).willThrow(DataException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.save(user)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.registerUser(userBO));

	}

	@Test
	@DisplayName("getUser operation : correct case")
	public void givenId_whenGetUser_thenReturnUserBO() {

		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}

		UserBO foundUser = null;
		try {
			foundUser = userService.getUser(1L);
		} catch (BusinessException e) {
		}

		assertThat(foundUser).isNotNull();
		assertThat(foundUser).isEqualTo(userBO);
	}

	@Test
	@DisplayName("getUser operation : incorrect case -> notFound")
	public void givenId_whenGetUser_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.getUser(1L));
	}

	@Test
	@DisplayName("deleteUser operation : correct case")
	public void givenUserBO_whenDeleteUser_thenDeleteUser() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.willDoNothing().given(userDAO).delete(user);
		} catch (DataException e) {
		}

		try {
			userService.deleteUser(1L);
		} catch (BusinessException e) {
		}

		try {
			verify(userDAO, times(1)).delete(user);
		} catch (DataException e) {
		}
	}

	@Test
	@DisplayName("deleteUser operation : incorrect case -> notFound")
	public void givenUserBOThatNotExists_whenDeleteUser_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.deleteUser(1L));

	}

	@Test
	@DisplayName("deleteUser operation : incorrect case -> user in active tournament")
	public void givenUserBOThatIsInActiveTournament_whenDeleteUser_thenThrowBusinessException() {

		userBO.getTeams().getFirst().getTournaments().getFirst().setState(TournamentState.EN_JUEGO);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);

		assertThrows(UserInActiveTournamentException.class, () -> userService.deleteUser(1L));

	}

	@Test
	@DisplayName("getAllUsers operation : correct case")
	public void givenNothing_whenGetAllUsers_thenReturnAllUsers() {

		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findAll()).willReturn(usersList);
		} catch (DataException e) {
		}

		List<UserBO> users = null;
		try {
			users = userService.getAllUsers();
		} catch (BusinessException e) {
		}

		assertThat(users).isNotNull();
		assertThat(users).isNotEmpty();
		assertThat(users.getFirst()).isEqualTo(userBO);
	}

	@Test
	@DisplayName("getAllUsers operation : incorrect case -> Exception")
	public void givenNothing_whenGetAllUsers_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findAll()).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.getAllUsers());
	}

	@Test
	@DisplayName("getUserByNick operation : correct case")
	public void givenNick_whenGetUserByNick_thenReturnUserBO() {

		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findByNick("test")).willReturn(user);
		} catch (DataException e) {
		}

		UserBO foundUserBO = null;
		try {
			foundUserBO = userService.getUserByNick("test");
		} catch (BusinessException e) {
		}

		assertThat(foundUserBO).isNotNull();
		assertThat(foundUserBO).isEqualTo(userBO);
	}

	@Test
	@DisplayName("getUserByNick operation : incorrect case -> not found")
	public void givenNick_whenGetUserByNick_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findByNick("test")).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.getUserByNick("test"));
	}

	@Test
	@DisplayName("getUserByEmail operation : correct case")
	public void givenEmail_whenGetUserByEmail_thenReturnUserBO() {

		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findByEmail("test@email.com")).willReturn(user);
		} catch (DataException e) {
		}

		UserBO foundUserBO = null;
		try {
			foundUserBO = userService.getUserByEmail("test@email.com");
		} catch (BusinessException e) {
		}

		assertThat(foundUserBO).isNotNull();
		assertThat(foundUserBO).isEqualTo(userBO);
	}

	@Test
	@DisplayName("getUserByEmail operation : incorrect case -> not found")
	public void givenEmail_whenGetUserByEmail_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findByEmail("test@email.com")).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.getUserByEmail("test@email.com"));
	}

	@Test
	@DisplayName("getUsersByState operation : correct case")
	public void givenNothing_whenGetUsersByState_thenReturnUsers() {

		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findByState(UserState.CONECTADO)).willReturn(usersList);
		} catch (DataException e) {
		}

		List<UserBO> users = null;
		try {
			users = userService.getUsersByState(UserState.CONECTADO);
		} catch (BusinessException e) {
		}

		assertThat(users).isNotNull();
		assertThat(users).isNotEmpty();
		assertThat(users.getFirst()).isEqualTo(userBO);
	}

	@Test
	@DisplayName("getUsersByState operation : incorrect case -> Exception")
	public void givenNothing_whenGetUsersByState_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findByState(UserState.CONECTADO)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.getUsersByState(UserState.CONECTADO));
	}

	@Test
	@DisplayName("updateUser operation : correct case")
	public void givenIdAndAvatar_whenUpdateUser_thenReturnUpdatedUserBO() {

		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.save(user)).willReturn(user);
		} catch (DataException e) {
		}

		UserBO updatedUser = null;
		try {
			updatedUser = userService.UpdateUser(1L, "prueba", "pass");
		} catch (BusinessException e) {
		}

		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser.getAvatar()).isEqualTo("prueba", "pass");
	}

	@Test
	@DisplayName("updateUser operation : incorrect case -> user not found")
	public void givenIdThatNotExists_whenUpdateUser_thenThrowsBusinessException() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.UpdateUser(1L, "prueba", "pass"));

	}

	@Test
	@DisplayName("updateUser operation : incorrect case -> user not updated")
	public void givenIdAndAvatar_whenUpdateUser_thenThrowsBusinessException() {

		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.save(user)).willThrow(DataException.class);
		} catch (DataException e) {
		}
		assertThrows(BusinessException.class, () -> userService.UpdateUser(1L, "prueba", "pass"));

	}

	@Test
	@DisplayName("updateRoleUser operation : correct case")
	public void givenIdAndListRoles_whenUpdateUser_thenReturnUpdatedUserBO() {

		RoleBO role = RoleBO.builder().id(1L).name("Jugador").description("Rol de jugador").build();
		List<RoleBO> roles = new ArrayList<RoleBO>();
		roles.add(role);
		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);

		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.save(user)).willReturn(user);
		} catch (DataException e) {
		}

		UserBO updatedUser = null;
		try {
			updatedUser = userService.UpdateRoleUser(1L, roles);
		} catch (BusinessException e) {
		}

		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser.getRoles().getFirst()).isEqualTo(role);
	}

	@Test
	@DisplayName("updateRoleUser operation : incorrect case -> user not found")
	public void givenIdThatNotExists_whenUpdateRoleUser_thenThrowsBusinessException() {

		RoleBO role = RoleBO.builder().id(1L).name("Jugador").description("Rol de jugador").build();
		List<RoleBO> roles = new ArrayList<RoleBO>();
		roles.add(role);

		try {
			BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.UpdateRoleUser(1L, roles));

	}

	@Test
	@DisplayName("updateRoleUser operation : incorrect case -> roles null")
	public void givenIdAndListRolesNull_whenUpdateRoleUser_thenThrowsBusinessException() {

		RoleBO role = null;
		List<RoleBO> roles = new ArrayList<RoleBO>();
		roles.add(role);

		assertThrows(BusinessException.class, () -> userService.UpdateRoleUser(1L, roles));
	}

	@Test
	@DisplayName("updateRoleUser operation : incorrect case -> user not updated")
	public void givenIdAndListRoles_whenUpdateRoleUser_thenThrowsBusinessException() {

		RoleBO role = RoleBO.builder().id(1L).name("Jugador").description("Rol de jugador").build();
		List<RoleBO> roles = new ArrayList<RoleBO>();
		roles.add(role);
		BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
		BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);

		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(userDAO.save(user)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> userService.UpdateRoleUser(1L, roles));

	}

}