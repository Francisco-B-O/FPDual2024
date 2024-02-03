package com.Dual2024.ProjectCompetition.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.Dual2024.ProjectCompetition.Model.Modality;
import com.Dual2024.ProjectCompetition.Model.Team;
import com.Dual2024.ProjectCompetition.Model.User;
import com.Dual2024.ProjectCompetition.Model.UserState;

@DataJpaTest(showSql = false)
@Order(4)
public class TeamRepositoryTest {
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModalityRepository modalityRepository;
	private Team team;

	@BeforeEach
	public void setup() {
		User user = new User();
		user.setEmail("test@email.com");
		user.setNick("test");
		user.setPassword("passwordTest");
		user.setState(UserState.CONECTADO);

		User user2 = new User();
		user2.setEmail("test2@email.com");
		user2.setNick("test2");
		user2.setPassword("passwordTest2");
		user2.setState(UserState.CONECTADO);

		Modality modality = new Modality();
		modality.setName("modality1");
		modality.setNumberPlayers(2);

		modalityRepository.save(modality);
		userRepository.save(user);
		userRepository.save(user2);

		team = new Team();
		team.setName("TestTeam");
		team.setUsers(userRepository.findAll());
		team.setModality(modality);

	}

	@Test
	@DisplayName("JUnit test for save operation")
	public void givenTeamObject_whenSave_theReturnSavedTeam() {
		Team savedTeam = teamRepository.save(team);
		assertThat(savedTeam).isNotNull();
		assertThat(savedTeam.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("JUnit test for findAll operation")
	public void givenTeams_whenFindAll_thenReturnAllTeams() {
		User user3 = new User();
		user3.setEmail("test@email3.com");
		user3.setNick("test3");
		user3.setPassword("passwordTest3");
		user3.setState(UserState.CONECTADO);
		User user4 = new User();
		user4.setEmail("test@email4.com");
		user4.setNick("test4");
		user4.setPassword("passwordTest4");
		user4.setState(UserState.CONECTADO);
		Modality modality2 = new Modality();
		modality2.setName("modality2");
		modality2.setNumberPlayers(2);
		modalityRepository.save(modality2);
		userRepository.save(user3);
		userRepository.save(user4);
		Team team2 = new Team();
		team2.setName("TestTeam2");
		team2.setUsers(userRepository.findAll());
		team2.setModality(modality2);
		teamRepository.save(team);
		teamRepository.save(team2);
		List<Team> teams = teamRepository.findAll();
		assertThat(teams).isNotNull();
		assertThat(teams.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for findByName operation")
	public void givenTeam_whenFindByName_thenReturnTeam() {
		teamRepository.save(team);
		Team team = teamRepository.findByName("TestTeam");
		assertThat(team).isNotNull();
		assertThat(team.getName()).isEqualTo("TestTeam");
	}

	@Test
	@DisplayName("JUnit test for findByModality operation")
	public void givenModality_whenFindByModality_thenReturnTeams() {
		teamRepository.save(team);
		List<Team> teams = teamRepository.findByModality(modalityRepository.findByName("modality1"));
		assertThat(teams).isNotNull();
		assertThat(teams.size()).isEqualTo(1);
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
		assertThat(savedUpdatedTeam.getId()).isEqualTo(team.getId());

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
