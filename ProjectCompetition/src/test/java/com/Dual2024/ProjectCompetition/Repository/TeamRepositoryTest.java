package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.ModalityRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TeamRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.UserRepository;

@DataJpaTest(showSql = false)
public class TeamRepositoryTest {
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModalityRepository modalityRepository;
	private Team team, team2, duplicatedNameModalityTeam;
	private User user, user2, user3, user4;
	private Modality modality;

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
		modalityRepository.save(modality);

		team = Team.builder().name("TestTeam").users(users1).modality(modality).build();
		team2 = Team.builder().name("TestTeam2").users(users2).modality(modality).build();
		duplicatedNameModalityTeam = Team.builder().name("TestTeam").users(users2).modality(modality).build();

	}

	@Test
	@DisplayName("JUnit test for findById operation")
	public void givenId_whenFindById_theReturnTeam() {

		Team savedTeam = teamRepository.save(team);

		Team foundTeam = teamRepository.findById(team.getId()).get();

		assertThat(foundTeam).isNotNull();
		assertThat(foundTeam).isEqualTo(savedTeam);
	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenTeamObject_whenSave_theReturnSavedTeam() {

		Team savedTeam = teamRepository.save(team);
		try {
			teamRepository.save(duplicatedNameModalityTeam);
		} catch (DataIntegrityViolationException e) {
			assertThat(e).isNotNull();
		}

		assertThat(savedTeam).isNotNull();
		assertThat(savedTeam.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenTeams_whenFindAll_thenReturnAllTeams() {

		teamRepository.save(team);
		teamRepository.save(team2);

		List<Team> teams = teamRepository.findAll();

		assertThat(teams).isNotNull();
		assertThat(teams.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenTeam_whenFindByName_thenReturnTeam() {

		Team savedTeam = teamRepository.save(team);

		Team foundTeam = teamRepository.findByName("TestTeam");

		assertThat(foundTeam).isNotNull();
		assertThat(foundTeam).isEqualTo(savedTeam);
	}

	@Test
	@DisplayName("JUnit test for findByModality operation")
	public void givenModality_whenFindByModality_thenReturnTeams() {

		teamRepository.save(team);
		teamRepository.save(team2);

		List<Team> teams = teamRepository.findByModality(modalityRepository.findByName("modality1"));
		assertThat(teams).isNotNull();

		assertThat(teams.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for update operation")
	public void givenTeam_whenUpdate_thenUpdateTeam() {

		teamRepository.save(team);
		Team updatedTeam = new Team();
		updatedTeam.setId(team.getId());
		updatedTeam.setName("equipo");

		Team savedUpdatedTeam = teamRepository.save(updatedTeam);

		assertThat(savedUpdatedTeam).isNotNull();
		assertThat(savedUpdatedTeam).isEqualTo(updatedTeam);

	}

	@Test
	@DisplayName("JUnit test for delete operation")
	public void givenTeam_whenDelete_thenRemoveTeam() {
		teamRepository.save(team);

		teamRepository.delete(team);

		Team deletedTeam = teamRepository.findByName("TestTeam");
		assertThat(deletedTeam).isNull();

	}

}
