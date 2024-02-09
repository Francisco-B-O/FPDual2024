package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.FormatRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.ModalityRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TeamRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TournamentRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.UserRepository;

@DataJpaTest(showSql = false)
@Order(6)
public class TournamentRepositoryTest {
	@Autowired
	private TournamentRepository tournamentRepository;
	@Autowired
	private ModalityRepository modalityRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	FormatRepository formatRepository;
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
		users1.add(userRepository.save(user));
		users1.add(userRepository.save(user2));
		List<User> users2 = new ArrayList<User>();
		users2.add(userRepository.save(user3));
		users2.add(userRepository.save(user4));

		modality = Modality.builder().name("modality1").numberPlayers(2).build();
		savedModality = modalityRepository.save(modality);

		team = Team.builder().name("TestTeam").users(users1).modality(modality).build();
		team2 = Team.builder().name("TestTeam2").users(users2).modality(modality).build();
		List<Team> teams = new ArrayList<Team>();

		teams.add(teamRepository.save(team));
		teams.add(teamRepository.save(team2));

		format = Format.builder().name("torneo").build();
		savedFormat = formatRepository.save(format);

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
	@DisplayName("JUnit test for findById operation")
	public void givenId_whenFindById_theReturnTournament() {

		Tournament savedTournament = tournamentRepository.save(tournament);

		Tournament foundTournament = tournamentRepository.findById(tournament.getId()).get();

		assertThat(foundTournament).isNotNull();
		assertThat(foundTournament).isEqualTo(savedTournament);

	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenTournament_whenSave_thenReturnSavedTournament() {

		Tournament savedTournament = tournamentRepository.save(tournament);
		try {
			tournamentRepository.save(duplicatedNameModalityTournament);
		} catch (DataIntegrityViolationException e) {
			assertThat(e).isNotNull();
		}

		assertThat(savedTournament).isNotNull();
		assertThat(savedTournament.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenTournaments_whenFindAll_thenReturnAllTournaments() {

		tournamentRepository.save(tournament);
		tournamentRepository.save(tournament2);

		List<Tournament> tournaments = tournamentRepository.findAll();

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenTournamentName_whenFindByName_thenReturnTournament() {

		Tournament savedTournament = tournamentRepository.save(tournament);

		Tournament foundTournament = tournamentRepository.findByName("Torneo de futbol");

		assertThat(tournament).isNotNull();
		assertThat(savedTournament).isEqualTo(foundTournament);
	}

	@Test
	@DisplayName("JUnit test for findByFormat operation")
	public void givenFormat_whenFindByFormat_thenReturnTournaments() {

		tournamentRepository.save(tournament);

		List<Tournament> tournaments = tournamentRepository.findByFormat(savedFormat);

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findBySize operation")
	public void givenSize_whenFindBySize_thenReturnTournaments() {

		tournamentRepository.save(tournament);

		List<Tournament> tournaments = tournamentRepository.findBySize(2);

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findByStartDate operation")
	public void givenStartDate_whenFindByStartDate_thenReturnTournaments() {

		tournamentRepository.save(tournament);

		List<Tournament> tournaments = tournamentRepository.findByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findByEndDate operation")
	public void givenEndDate_whenFindByEndDate_thenReturnTournaments() {

		tournamentRepository.save(tournament);

		List<Tournament> tournaments = tournamentRepository.findByEndDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0));

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findByState operation")
	public void givenState_whenFindByState_thenReturnTournaments() {

		tournamentRepository.save(tournament);

		List<Tournament> tournaments = tournamentRepository.findByState(TournamentState.EN_JUEGO);

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findByModality operation")
	public void givenModality_whenFindByModality_thenReturnTournaments() {

		tournamentRepository.save(tournament);

		List<Tournament> tournaments = tournamentRepository.findByModality(savedModality);

		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenTournament_whenUpdate_thenReturnUpdatedTournament() {

		tournamentRepository.save(tournament);
		Tournament updatedTournament = new Tournament();
		updatedTournament.setId(tournament.getId());
		updatedTournament.setName("Torneo de fútbol updated");

		Tournament savedUpdatedTournament = tournamentRepository.save(updatedTournament);

		assertThat(savedUpdatedTournament).isNotNull();
		assertThat(savedUpdatedTournament).isEqualTo(updatedTournament);
	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenTeam_whenDelete_thenRemoveTeam() {

		tournamentRepository.save(tournament);
		tournamentRepository.delete(tournament);

		Tournament deletedTournament = tournamentRepository.findByName("Torneo de futbol");

		assertThat(deletedTournament).isNull();

	}

}
