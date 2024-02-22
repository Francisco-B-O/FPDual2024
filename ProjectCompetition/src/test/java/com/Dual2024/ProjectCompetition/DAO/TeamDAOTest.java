package com.Dual2024.ProjectCompetition.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.Dual2024.ProjectCompetition.DataAccess.DAO.ModalityDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TeamDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.DataAccess.DAO")
public class TeamDAOTest {
	@Autowired
	private TeamDAO teamDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ModalityDAO modalityDAO;
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
			modalityDAO.save(modality);
		} catch (DataException e) {
			e.printStackTrace();
		}

		team = Team.builder().name("TestTeam").users(users1).captain(user).modality(modality).build();
		team2 = Team.builder().name("TestTeam2").users(users2).captain(user2).modality(modality).build();
		duplicatedNameModalityTeam = Team.builder().name("TestTeam").captain(user3).users(users2).modality(modality)
				.build();

	}

	@Test
	@DisplayName("findById operation")
	public void givenId_whenFindById_theReturnTeam() {

		Team savedTeam = null;
		try {
			savedTeam = teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}

		Team foundTeam = null;
		try {
			foundTeam = teamDAO.findById(team.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(foundTeam).isNotNull();
		assertThat(foundTeam).isEqualTo(savedTeam);
	}

	@Test
	@DisplayName("save operation")
	public void givenTeamObject_whenSave_theReturnSavedTeam() {

		Team savedTeam = null;
		try {
			savedTeam = teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> teamDAO.save(duplicatedNameModalityTeam));
		assertThat(savedTeam).isNotNull();
		assertThat(savedTeam.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("findAll operation")
	public void givenTeams_whenFindAll_thenReturnAllTeams() {

		try {
			teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			teamDAO.save(team2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Team> teams = null;
		try {
			teams = teamDAO.findAll();
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(teams).isNotNull();
		assertThat(teams.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("findByName operation")
	public void givenTeam_whenFindByName_thenReturnTeam() {

		try {
			teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			teamDAO.save(team2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Team> teams = null;
		try {
			teams = teamDAO.findByName("TestTeam");
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(teams).isNotNull();
		assertThat(teams.size()).isEqualTo(1);

	}

	@Test
	@DisplayName("findByModality operation")
	public void givenModality_whenFindByModality_thenReturnTeams() {

		try {
			teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			teamDAO.save(team2);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Team> teams = null;
		try {
			teams = teamDAO.findByModality(modalityDAO.findByName("modality1"));
		} catch (DataException e) {
			e.printStackTrace();
		}
		assertThat(teams).isNotNull();

		assertThat(teams.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("update operation")
	public void givenTeam_whenUpdate_thenUpdateTeam() {

		try {
			teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}
		Team updatedTeam = new Team();
		updatedTeam.setId(team.getId());
		updatedTeam.setName("equipo");

		Team savedUpdatedTeam = null;
		try {
			savedUpdatedTeam = teamDAO.save(updatedTeam);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThat(savedUpdatedTeam).isNotNull();
		assertThat(savedUpdatedTeam).isEqualTo(updatedTeam);

	}

	@Test
	@DisplayName("delete operation")
	public void givenTeam_whenDelete_thenRemoveTeam() {
		try {
			teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}

		try {
			teamDAO.delete(team);
		} catch (DataException e) {
			e.printStackTrace();
		}

		assertThrows(DataException.class, () -> teamDAO.findByName("TestTeam"));

	}

	@Test
	@DisplayName("findByCaptain operation")
	public void givenModality_whenFindByCaptain_thenReturnTeams() {

		try {
			teamDAO.save(team);
		} catch (DataException e) {
			e.printStackTrace();
		}

		List<Team> teams = null;
		try {
			teams = teamDAO.findByCaptain(userDAO.findByNick("test"));
		} catch (DataException e) {
			e.printStackTrace();
		}
		assertThat(teams).isNotNull();

		assertThat(teams.size()).isEqualTo(1);
	}

}
