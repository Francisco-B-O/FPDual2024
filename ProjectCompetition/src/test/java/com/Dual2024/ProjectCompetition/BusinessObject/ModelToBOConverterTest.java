package com.Dual2024.ProjectCompetition.BusinessObject;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@SpringBootTest
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.Business.BusinessObject")
public class ModelToBOConverterTest {
	private static User user;
	private static Role role;
	private static Modality modality;
	private static Format format;
	private static Team team;
	private static Tournament tournament;

	@Autowired
	private ModelToBOConverter modelToBOConverter;

	@BeforeAll
	public static void setup() {

		role = Role.builder().id(1L).name("test").description("Test role").build();
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);

		user = User.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
				.state(UserState.CONECTADO).roles(roles).build();
		List<User> users = new ArrayList<User>();
		users.add(user);

		modality = Modality.builder().id(1L).name("modality1").numberPlayers(2).build();

		team = Team.builder().id(1L).name("TestTeam").captain(user).users(users).modality(modality).build();
		List<Team> teams = new ArrayList<Team>();
		teams.add(team);
		user.setTeams(teams);

		format = Format.builder().id(1L).name("torneo").build();

		tournament = Tournament.builder().id(1L).name("Torneo de futbol").size(2).description("El mejor futbol")
				.format(format).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
				.endDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0)).state(TournamentState.EN_JUEGO).modality(modality)
				.teams(teams).build();

	}

	@Test
	@DisplayName("User -> UserBO")
	public void givenUser_whenUserModelToBO_thenReturnUserBO() {

		UserBO userTest = modelToBOConverter.userModelToBO(user);
		assertThat(userTest.getId()).isEqualTo(user.getId());
		assertThat(userTest.getEmail()).isEqualTo(user.getEmail());
		assertThat(userTest.getNick()).isEqualTo(user.getNick());
		assertThat(userTest.getPassword()).isEqualTo(user.getPassword());
		assertThat(userTest.getState()).isEqualTo(UserState.CONECTADO);
		assertThat(userTest.getRoles().getFirst().getId()).isEqualTo(user.getRoles().getFirst().getId());
		assertThat(userTest.getTeams().getFirst().getId()).isEqualTo(user.getTeams().getFirst().getId());
	}

	@Test
	@DisplayName("Role -> RoleBO")
	public void givenRole_whenRoleModelToBO_thenReturnRoleBO() {

		RoleBO roleTest = modelToBOConverter.roleModelToBO(role);
		assertThat(roleTest.getId()).isEqualTo(role.getId());
		assertThat(roleTest.getName()).isEqualTo(role.getName());
		assertThat(roleTest.getDescription()).isEqualTo(role.getDescription());
	}

	@Test
	@DisplayName("Modality -> ModalityBO")
	public void givenModality_whenModalityModelToBO_thenReturnModalityBO() {

		ModalityBO modalityTest = modelToBOConverter.modalityModelToBO(modality);

		assertThat(modalityTest.getId()).isEqualTo(modality.getId());
		assertThat(modalityTest.getName()).isEqualTo(modality.getName());
		assertThat(modalityTest.getNumberPlayers()).isEqualTo(modality.getNumberPlayers());
	}

	@Test
	@DisplayName("Format -> FormatBO")
	public void givenFormat_whenFormatToBO_thenReturnFormatBO() {

		FormatBO formatTest = modelToBOConverter.formatModelToBO(format);

		assertThat(formatTest.getId()).isEqualTo(format.getId());
		assertThat(formatTest.getName()).isEqualTo(format.getName());
	}

	@Test
	@DisplayName("Tournametn -> TournamentBO")
	public void givenTournament_whenTournamentModelToBO_thenReturnTournamentBO() {

		TournamentBO tournamentTest = modelToBOConverter.tournamentModelToBO(tournament);

		assertThat(tournamentTest.getId()).isEqualTo(tournament.getId());
		assertThat(tournamentTest.getDescription()).isEqualTo(tournament.getDescription());
		assertThat(tournamentTest.getName()).isEqualTo(tournament.getName());
		assertThat(tournamentTest.getStartDate()).isEqualTo(tournament.getStartDate());
		assertThat(tournamentTest.getEndDate()).isEqualTo(tournament.getEndDate());
		assertThat(tournamentTest.getState()).isEqualTo(TournamentState.EN_JUEGO);
		assertThat(tournamentTest.getSize()).isEqualTo(tournament.getSize());
		assertThat(tournamentTest.getModality().getId()).isEqualTo(tournament.getModality().getId());
		assertThat(tournamentTest.getFormat().getId()).isEqualTo(tournament.getFormat().getId());
		assertThat(tournamentTest.getTeams().getFirst().getId()).isEqualTo(tournament.getTeams().getFirst().getId());

	}

	@Test
	@DisplayName("Team -> TeamBO")
	public void givenTeam_whenTeamModelToBO_thenReturnTeamBO() {

		TeamBO teamTest = modelToBOConverter.teamModelToBO(team);

		assertThat(teamTest.getId()).isEqualTo(team.getId());
		assertThat(teamTest.getName()).isEqualTo(team.getName());
		assertThat(teamTest.getUsers().getFirst().getId()).isEqualTo(team.getUsers().getFirst().getId());
		assertThat(teamTest.getModality().getId()).isEqualTo(team.getModality().getId());
	}

	@Test
	@DisplayName("User -> UserBOAux")
	public void givenUser_whenUserModelToBOAux_thenReturnUserBOAux() {

		UserBOAux userTest = modelToBOConverter.userModelToBOAux(user);

		assertThat(userTest.getId()).isEqualTo(user.getId());
		assertThat(userTest.getEmail()).isEqualTo(user.getEmail());
		assertThat(userTest.getNick()).isEqualTo(user.getNick());
		assertThat(userTest.getPassword()).isEqualTo(user.getPassword());
		assertThat(userTest.getState()).isEqualTo(UserState.CONECTADO);
		assertThat(userTest.getRoles().getFirst().getId()).isEqualTo(user.getRoles().getFirst().getId());
	}

	@Test
	@DisplayName("Tournament -> TournamentBOAux")
	public void givenTournament_whenTournamentModelToBOAux_thenReturnTournamentBOAux() {

		TournamentBOAux tournamentTest = modelToBOConverter.tournamentModelToBOAux(tournament);

		assertThat(tournamentTest.getId()).isEqualTo(tournament.getId());
		assertThat(tournamentTest.getDescription()).isEqualTo(tournament.getDescription());
		assertThat(tournamentTest.getName()).isEqualTo(tournament.getName());
		assertThat(tournamentTest.getStartDate()).isEqualTo(tournament.getStartDate());
		assertThat(tournamentTest.getEndDate()).isEqualTo(tournament.getEndDate());
		assertThat(tournamentTest.getState()).isEqualTo(TournamentState.EN_JUEGO);
		assertThat(tournamentTest.getSize()).isEqualTo(tournament.getSize());
		assertThat(tournamentTest.getModality().getId()).isEqualTo(tournament.getModality().getId());
		assertThat(tournamentTest.getFormat().getId()).isEqualTo(tournament.getFormat().getId());

	}

}
