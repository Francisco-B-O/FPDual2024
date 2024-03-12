package com.dual2024.projectcompetition.DAO;

import com.dual2024.projectcompetition.dataaccess.dao.ModalityDAO;
import com.dual2024.projectcompetition.dataaccess.dao.TeamDAO;
import com.dual2024.projectcompetition.dataaccess.dao.UserDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.Modality;
import com.dual2024.projectcompetition.dataaccess.model.Team;
import com.dual2024.projectcompetition.dataaccess.model.User;
import com.dual2024.projectcompetition.utils.UserState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @BeforeEach
    public void setup() throws DataException {
        User user = User.builder().email("test@email.com").nick("test").password("passwordTest").state(UserState.CONNECTED)
                .build();
        User user2 = User.builder().email("test2@email.com").nick("test2").password("passwordTest")
                .state(UserState.CONNECTED).build();
        User user3 = User.builder().email("test3@email.com").nick("test3").password("passwordTest")
                .state(UserState.CONNECTED).build();
        User user4 = User.builder().email("test4@email.com").nick("test4").password("passwordTest")
                .state(UserState.CONNECTED).build();
        List<User> users1 = new ArrayList<User>();
        users1.add(userDAO.save(user));
        users1.add(userDAO.save(user2));
        List<User> users2 = new ArrayList<User>();
        users2.add(userDAO.save(user3));
        users2.add(userDAO.save(user4));
        Modality modality = Modality.builder().name("modality1").numberPlayers(2).build();
        modalityDAO.save(modality);
        team = Team.builder().name("TestTeam").users(users1).captain(user).modality(modality).build();
        team2 = Team.builder().name("TestTeam2").users(users2).captain(user2).modality(modality).build();
        duplicatedNameModalityTeam = Team.builder().name("TestTeam").captain(user3).users(users2).modality(modality)
                .build();
    }

    @Test
    @DisplayName("findById operation")
    public void givenId_whenFindById_theReturnTeam() throws DataException {

        Team savedTeam = teamDAO.save(team);

        Team foundTeam = teamDAO.findById(team.getId());

        assertThat(foundTeam).isNotNull();
        assertThat(foundTeam).isEqualTo(savedTeam);
    }

    @Test
    @DisplayName("save operation")
    public void givenTeamObject_whenSave_theReturnSavedTeam() throws DataException {

        Team savedTeam = teamDAO.save(team);

        assertThrows(DataException.class, () -> teamDAO.save(duplicatedNameModalityTeam));
        assertThat(savedTeam).isNotNull();
        assertThat(savedTeam.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenTeams_whenFindAll_thenReturnAllTeams() throws DataException {

        teamDAO.save(team);
        teamDAO.save(team2);

        List<Team> teams = teamDAO.findAll();

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByName operation")
    public void givenTeam_whenFindByName_thenReturnTeam() throws DataException {

        teamDAO.save(team);
        teamDAO.save(team2);

        List<Team> teams = teamDAO.findByName("TestTeam");

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("findByModality operation")
    public void givenModality_whenFindByModality_thenReturnTeams() throws DataException {

        teamDAO.save(team);
        teamDAO.save(team2);

        List<Team> teams = teamDAO.findByModality(modalityDAO.findByName("modality1"));

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("update operation")
    public void givenTeam_whenUpdate_thenUpdateTeam() throws DataException {

        teamDAO.save(team);
        Team updatedTeam = new Team();
        updatedTeam.setId(team.getId());
        updatedTeam.setName("equipo");

        Team savedUpdatedTeam = teamDAO.save(updatedTeam);

        assertThat(savedUpdatedTeam).isNotNull();
        assertThat(savedUpdatedTeam).isEqualTo(updatedTeam);

    }

    @Test
    @DisplayName("delete operation")
    public void givenTeam_whenDelete_thenRemoveTeam() throws DataException {

        teamDAO.save(team);

        teamDAO.delete(team.getId());

        assertThrows(DataException.class, () -> teamDAO.findByName("TestTeam"));

    }

    @Test
    @DisplayName("findByCaptain operation")
    public void givenTeam_whenFindByCaptain_thenReturnTeams() throws DataException {

        teamDAO.save(team);

        List<Team> teams = teamDAO.findByCaptain(userDAO.findByNick("test"));

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(1);
    }

}
