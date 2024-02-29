package com.Dual2024.ProjectCompetition.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.Dual2024.ProjectCompetition.DataAccess.DAO.FormatDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.ModalityDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TeamDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TournamentDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.DataAccess.DAO")
public class TournamentDAOTest {
	@Autowired
	private TournamentDAO tournamentDAO;
	@Autowired
	private ModalityDAO modalityDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TeamDAO teamDAO;
	@Autowired
	private FormatDAO formatDAO;
	private User user, user2, user3, user4;
	private Modality modality, savedModality;
	private Format format, savedFormat;
	private Team team, team2;
	private Tournament tournament, tournament2, duplicatedNameModalityTournament;

	@BeforeEach
	public void setup() {
		user = User.builder().email("test@email.com").nick("test").password("passwordTest").state(UserState.CONECTADO)
				.build();
		user2 = User.builder().email("test2@email.com").nick("test2").password("passwordTest")
				.state(UserState.CONECTADO).build();
		user3 = User.builder().email("test3@email.com").nick("test3").password("passwordTest")
				.state(UserState.CONECTADO).build();
		user4 = User.builder().email("test4@email.com").nick("test4").password("passwordTest")
				.state(UserState.CONECTADO).build();
		List<User> users1 = new ArrayList<User>();
		try {
			users1.add(userDAO.save(user));
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			users1.add(userDAO.save(user2));
		} catch (DataException e) {
			e.printStackTrace();
		}
		List<User> users2 = new ArrayList<User>();
		try {
			users2.add(userDAO.save(user3));
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			users2.add(userDAO.save(user4));
		} catch (DataException e) {
			e.printStackTrace();
		}

		modality = Modality.builder().name("modality1").numberPlayers(2).build();
		try {
			savedModality = modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		team = Team.builder().name("TestTeam").users(users1).captain(user).modality(modality).build();
		team2 = Team.builder().name("TestTeam2").users(users2).captain(user2).modality(modality).build();
		List<Team> teams = new ArrayList<Team>();

		try {
			teams.add(teamDAO.save(team));
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			teams.add(teamDAO.save(team2));
		} catch (DataException e) {
			e.printStackTrace();
		}

		format = Format.builder().name("torneo").build();
		try {
			savedFormat = formatDAO.save(format);
		} catch (DataException e) {
			e.printStackTrace();
		}

		tournament = Tournament.builder().name("Torneo de futbol").size(2).description("El mejor futbol")
				.format(savedFormat).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
				.endDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0)).state(TournamentState.EN_JUEGO)
				.modality(savedModality).teams(teams).build();
		tournament2 = Tournament.builder().name("Torneo de tenis").size(2).description("El mejor tenis")
				.format(savedFormat).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
				.endDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0)).state(TournamentState.EN_JUEGO)
				.modality(savedModality).teams(teams).build();
		duplicatedNameModalityTournament = Tournament.builder().name("Torneo de futbol").size(2)
				.description("El mejor futbol del mundo").format(savedFormat)
				.startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0)).endDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0))
				.state(TournamentState.EN_JUEGO).modality(savedModality).teams(teams).build();
	}

	@Test
	@DisplayName("findById operation")
	public void givenId_whenFindById_theReturnTournament() {

		Tournament savedTournament = null;
		try {
			savedTournament = tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Tournament foundTournament = null;
		try {
			foundTournament = tournamentDAO.findById(tournament.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundTournament).isNotNull();
		assertThat(foundTournament).isEqualTo(savedTournament);

	}

	@Test
	@DisplayName("save operation")
	public void givenTournament_whenSave_thenReturnSavedTournament() {

		Tournament savedTournament = null;
		try {
			savedTournament = tournamentDAO.save(tournament);
		} catch (DataException e) {

			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> tournamentDAO.save(duplicatedNameModalityTournament));
		assertThat(savedTournament).isNotNull();
		assertThat(savedTournament.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findAll operation")
	public void givenTournaments_whenFindAll_thenReturnAllTournaments() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			tournamentDAO.save(tournament2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findAll();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("findByName operation")
	public void givenTournamentName_whenFindByName_thenReturnTournament() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			tournamentDAO.save(tournament2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findByName("Torneo de futbol");
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isEqualTo(1);

	}

	@Test
	@DisplayName("findByFormat operation")
	public void givenFormat_whenFindByFormat_thenReturnTournaments() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findByFormat(savedFormat);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findBySize operation")
	public void givenSize_whenFindBySize_thenReturnTournaments() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findBySize(2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findByStartDate operation")
	public void givenStartDate_whenFindByStartDate_thenReturnTournaments() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findByEndDate operation")
	public void givenEndDate_whenFindByEndDate_thenReturnTournaments() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findByEndDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0));
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findByState operation")
	public void givenState_whenFindByState_thenReturnTournaments() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findByState(TournamentState.EN_JUEGO);
		} catch (DataException e) {
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findByModality operation")
	public void givenModality_whenFindByModality_thenReturnTournaments() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Tournament> tournaments = null;
		try {
			tournaments = tournamentDAO.findByModality(savedModality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("update operation")
	public void givenTournament_whenUpdate_thenReturnUpdatedTournament() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}
		Tournament updatedTournament = new Tournament();
		updatedTournament.setId(tournament.getId());
		updatedTournament.setName("Torneo de fútbol updated");

		Tournament savedUpdatedTournament = null;
		try {
			savedUpdatedTournament = tournamentDAO.save(updatedTournament);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(savedUpdatedTournament).isNotNull();
		assertThat(savedUpdatedTournament).isEqualTo(updatedTournament);
	}

	@Test
	@DisplayName("delete operation")
	public void givenTeam_whenDelete_thenRemoveTeam() {

		try {
			tournamentDAO.save(tournament);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			tournamentDAO.delete(tournament.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> tournamentDAO.findByName("Torneo de futbol"));

	}
}
