package com.Dual2024.ProjectCompetition.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.*;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Business.Service.UserServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.RoleDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserBO userBO;
    private User user;
    private RoleBO roleBO;
    private Role role;
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
        TeamBO teamBO = TeamBO.builder().tournaments(tournamentsBO).build();
        List<TeamBO> teamsBO = new ArrayList<TeamBO>();
        teamsBO.add(teamBO);
        userBO.setTeams(teamsBO);
        usersList = new ArrayList<User>();
        usersList.add(user);
        roleBO = RoleBO.builder().id(1L).name("JUGADOR").description("Rol de jugador").build();
        role = Role.builder().id(1L).name("JUGADOR").description("Rol de jugador").build();
    }

    @Test
    @DisplayName("userRegister operation : correct case")
    public void givenUserBO_whenUserRegister_thenReturnUserBO() throws BusinessException, DataException {

        BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(modelToBOConverter.roleModelToBO(role)).willReturn(roleBO);
        BDDMockito.given(userDAO.findByNickOrEmail(userBO.getNick(), userBO.getEmail()))
                .willThrow(EntityNotFoundException.class);
        BDDMockito.given(roleDAO.findByName("JUGADOR")).willReturn(role);
        BDDMockito.given(userDAO.save(user)).willReturn(user);

        UserBO savedUser = userService.registerUser(userBO);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(userBO);
    }

    @Test
    @DisplayName("userRegister operation : incorrect case -> Duplicated email")
    public void givenUserBO_whenUserRegister_thenThrowDuplicatedEmailException() throws DataException {

        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findByNickOrEmail(userBO.getNick(), userBO.getEmail())).willReturn(usersList);

        assertThrows(DuplicatedEmailException.class, () -> userService.registerUser(userBO));
    }

    @Test
    @DisplayName("userRegister operation : incorrect case -> Duplicated nick")
    public void givenUserBO_whenUserRegister_thenThrowDuplicatedNickException() throws DataException {

        UserBO test = UserBO.builder().email("hola").id(1L).nick("test").password("passwordTest")
                .state(UserState.CONECTADO).build();
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findByNickOrEmail(test.getNick(), test.getEmail())).willReturn(usersList);

        assertThrows(DuplicatedNickException.class, () -> userService.registerUser(test));
    }

    @Test
    @DisplayName("userRegister operation : incorrect case -> Constraint violation (password in this case)")
    public void givenUserBO_whenUserRegister_thenThrowBusinessException() throws DataException {

        userBO.setPassword("p");
        user.setPassword("p");
        BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
        BDDMockito.given(userDAO.findByNickOrEmail(userBO.getNick(), userBO.getEmail()))
                .willThrow(EntityNotFoundException.class);
        BDDMockito.given(userDAO.save(user)).willThrow(DataException.class);

        assertThrows(BusinessException.class, () -> userService.registerUser(userBO));

    }

    @Test
    @DisplayName("getUser operation : correct case")
    public void givenId_whenGetUser_thenReturnUserBO() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);

        UserBO foundUser = userService.getUser(1L);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(userBO);
    }

    @Test
    @DisplayName("getUser operation : incorrect case -> notFound")
    public void givenId_whenGetUser_thenThrowBusinessException() throws DataException {

        BDDMockito.given(userDAO.findById(1L)).willThrow(EntityNotFoundException.class);


        assertThrows(UserNotFoundException.class, () -> userService.getUser(1L));
    }

    @Test
    @DisplayName("deleteUser operation : correct case")
    public void givenUserBO_whenDeleteUser_thenDeleteUser() throws BusinessException, DataException {

        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.willDoNothing().given(userDAO).delete(1L);

        userService.deleteUser(1L);

        verify(userDAO, times(1)).delete(1L);
    }

    @Test
    @DisplayName("deleteUser operation : incorrect case -> notFound")
    public void givenUserBOThatNotExists_whenDeleteUser_thenThrowBusinessException() throws DataException {

        BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);

        assertThrows(BusinessException.class, () -> userService.deleteUser(1L));

    }

    @Test
    @DisplayName("deleteUser operation : incorrect case -> user in active tournament")
    public void givenUserBOThatIsInActiveTournament_whenDeleteUser_thenThrowBusinessException() throws DataException {

        userBO.getTeams().getFirst().getTournaments().getFirst().setState(TournamentState.EN_JUEGO);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);

        assertThrows(UserInActiveTournamentException.class, () -> userService.deleteUser(1L));

    }

    @Test
    @DisplayName("getAllUsers operation : correct case")
    public void givenNothing_whenGetAllUsers_thenReturnAllUsers() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findAll()).willReturn(usersList);

        List<UserBO> users = userService.getAllUsers();

        assertThat(users).isNotNull();
        assertThat(users).isNotEmpty();
        assertThat(users.getFirst()).isEqualTo(userBO);
    }

    @Test
    @DisplayName("getAllUsers operation : incorrect case -> not found")
    public void givenNothing_whenGetAllUsers_thenThrowBusinessException() throws DataException {

        BDDMockito.given(userDAO.findAll()).willThrow(EntityNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.getAllUsers());
    }

    @Test
    @DisplayName("getUserByNick operation : correct case")
    public void givenNick_whenGetUserByNick_thenReturnUserBO() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findByNick("test")).willReturn(user);

        UserBO foundUserBO = userService.getUserByNick("test");

        assertThat(foundUserBO).isNotNull();
        assertThat(foundUserBO).isEqualTo(userBO);
    }

    @Test
    @DisplayName("getUserByNick operation : incorrect case -> not found")
    public void givenNick_whenGetUserByNick_thenThrowBusinessException() throws DataException {

        BDDMockito.given(userDAO.findByNick("test")).willThrow(EntityNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.getUserByNick("test"));
    }

    @Test
    @DisplayName("getUserByEmail operation : correct case")
    public void givenEmail_whenGetUserByEmail_thenReturnUserBO() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findByEmail("test@email.com")).willReturn(user);

        UserBO foundUserBO = userService.getUserByEmail("test@email.com");

        assertThat(foundUserBO).isNotNull();
        assertThat(foundUserBO).isEqualTo(userBO);
    }

    @Test
    @DisplayName("getUserByEmail operation : incorrect case -> not found")
    public void givenEmail_whenGetUserByEmail_thenThrowBusinessException() throws DataException {

        BDDMockito.given(userDAO.findByEmail("test@email.com")).willThrow(EntityNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail("test@email.com"));
    }

    @Test
    @DisplayName("getUsersByState operation : correct case")
    public void givenNothing_whenGetUsersByState_thenReturnUsers() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findByState(UserState.CONECTADO)).willReturn(usersList);

        List<UserBO> users = userService.getUsersByState(UserState.CONECTADO);

        assertThat(users).isNotNull();
        assertThat(users).isNotEmpty();
        assertThat(users.getFirst()).isEqualTo(userBO);
    }

    @Test
    @DisplayName("getUsersByState operation : incorrect case -> not found")
    public void givenNothing_whenGetUsersByState_thenThrowBusinessException() throws DataException {

        BDDMockito.given(userDAO.findByState(UserState.CONECTADO)).willThrow(EntityNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.getUsersByState(UserState.CONECTADO));
    }

    @Test
    @DisplayName("updateUser operation : correct case")
    public void givenIdAndAvatar_whenUpdateUser_thenReturnUpdatedUserBO() throws BusinessException, DataException {

        BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(userDAO.save(user)).willReturn(user);

        UserBO updatedUser = userService.updateUser(1L, "prueba", "pass");

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getAvatar()).isEqualTo("prueba", "pass");
    }

    @Test
    @DisplayName("updateUser operation : incorrect case -> user not found")
    public void givenIdThatNotExists_whenUpdateUser_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(userDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, "prueba", "pass"));

    }

    @Test
    @DisplayName("updateUser operation : incorrect case -> user not updated")
    public void givenIdAndAvatar_whenUpdateUser_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(userDAO.save(user)).willThrow(DataException.class);

        assertThrows(BusinessException.class, () -> userService.updateUser(1L, "prueba", "pass"));

    }

    @Test
    @DisplayName("updateRoleUser operation : correct case")
    public void givenIdAndListRoles_whenUpdateUser_thenReturnUpdatedUserBO() throws BusinessException, DataException {

        RoleBO role = RoleBO.builder().id(1L).name("Jugador").description("Rol de jugador").build();
        List<RoleBO> roles = new ArrayList<RoleBO>();
        roles.add(role);
        BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(userDAO.save(user)).willReturn(user);

        UserBO updatedUser = userService.updateRoleUser(1L, roles);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getRoles().getFirst()).isEqualTo(role);
    }

    @Test
    @DisplayName("updateRoleUser operation : incorrect case -> user not found")
    public void givenIdThatNotExists_whenUpdateRoleUser_thenThrowsBusinessException() throws DataException {

        RoleBO role = RoleBO.builder().id(1L).name("Jugador").description("Rol de jugador").build();
        List<RoleBO> roles = new ArrayList<RoleBO>();
        roles.add(role);
        BDDMockito.given(userDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.updateRoleUser(1L, roles));

    }


    @Test
    @DisplayName("updateRoleUser operation : incorrect case -> user not updated")
    public void givenIdAndListRoles_whenUpdateRoleUser_thenThrowsBusinessException() throws DataException {

        RoleBO role = RoleBO.builder().id(1L).name("Jugador").description("Rol de jugador").build();
        List<RoleBO> roles = new ArrayList<RoleBO>();
        roles.add(role);
        BDDMockito.given(boToModelConverter.userBOToModel(userBO)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBO(user)).willReturn(userBO);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(userDAO.save(user)).willThrow(DataException.class);

        assertThrows(BusinessException.class, () -> userService.updateRoleUser(1L, roles));

    }

}