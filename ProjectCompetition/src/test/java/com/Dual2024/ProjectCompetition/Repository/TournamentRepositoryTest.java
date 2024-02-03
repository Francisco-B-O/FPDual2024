package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Dual2024.ProjectCompetition.Model.Format;
import com.Dual2024.ProjectCompetition.Model.Modality;
import com.Dual2024.ProjectCompetition.Model.Team;
import com.Dual2024.ProjectCompetition.Model.Tournament;
import com.Dual2024.ProjectCompetition.Model.TournamentState;
import com.Dual2024.ProjectCompetition.Model.User;
import com.Dual2024.ProjectCompetition.Model.UserState;


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
	private Tournament tournament;

	@BeforeEach
	public void setup() {
		User user = new User();
		user.setEmail("Usertest@email.com");
		user.setNick("Usertest");
		user.setPassword("passwordTest");
		user.setState(UserState.CONECTADO);

		User user2 = new User();
		user2.setEmail("Usertest2@email.com");
		user2.setNick("Usertest2");
		user2.setPassword("passwordTest2");
		user2.setState(UserState.CONECTADO);

		userRepository.save(user);
		userRepository.save(user2);

		Modality modality = new Modality();
		modality.setName("modality1");
		modality.setNumberPlayers(2);

		modalityRepository.save(modality);

		Team team = new Team();
		team.setName("TestTeam");
		team.setUsers(userRepository.findAll());
		team.setModality(modality);
		Team team2 = new Team();
		team2.setName("TestTeam2");
		team2.setUsers(userRepository.findAll());
		team2.setModality(modality);

		teamRepository.save(team);
		teamRepository.save(team2);

		Format format = new Format();
		format.setName("torneo");
		formatRepository.save(format);

		tournament = new Tournament();
		tournament.setName("Torneo de futbol");
		tournament.setSize(2);
		tournament.setDescription("El mejor futbol");
		tournament.setFormat(format);
		tournament.setStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));
		tournament.setEndDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0));
		tournament.setState(TournamentState.EN_JUEGO);
		tournament.setModality(modality);
		tournament.setTeams(teamRepository.findAll());
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenTournament_whenSave_thenReturnSavedTournament() {
		Tournament savedTournament = tournamentRepository.save(tournament);
		assertThat(savedTournament).isNotNull();
		assertThat(savedTournament.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenTournaments_whenFindAll_thenReturnAllTournaments() {
		tournamentRepository.save(tournament);
		List<Tournament> tournaments = tournamentRepository.findAll();
		assertThat(tournaments).isNotNull();
		assertThat(tournaments.size()).isEqualTo(1);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenTournamentName_whenFindByName_thenReturnTournament() {
		tournamentRepository.save(tournament);
		Tournament tournament = tournamentRepository.findByName("Torneo de futbol");
		assertThat(tournament).isNotNull();
		assertThat(tournament.getName()).isEqualTo("Torneo de futbol");
	}

	@Test
	@DisplayName("JUnit test for findByFormat operation")
	public void givenFormat_whenFindByFormat_thenReturnTournaments() {
		tournamentRepository.save(tournament);
		List<Tournament> tournaments = tournamentRepository.findByFormat(formatRepository.findByName("torneo"));
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
		List<Tournament> tournaments = tournamentRepository.findByModality(modalityRepository.findByName("modality1"));
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
		updatedTournament.setSize(3);
		updatedTournament.setFormat(formatRepository.findByName("torneo"));
		updatedTournament.setStartDate(LocalDateTime.of(2022, 7, 1, 10, 0, 0));
		updatedTournament.setEndDate(LocalDateTime.of(2022, 7, 31, 18, 0, 0));
		updatedTournament.setState(TournamentState.EN_JUEGO);
		updatedTournament.setModality(modalityRepository.findByName("modality1"));
		updatedTournament.setTeams(teamRepository.findAll());
		Tournament savedUpdatedTournament = tournamentRepository.save(updatedTournament);
		assertThat(savedUpdatedTournament).isNotNull();
		assertThat(savedUpdatedTournament.getId()).isEqualTo(tournament.getId());
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
