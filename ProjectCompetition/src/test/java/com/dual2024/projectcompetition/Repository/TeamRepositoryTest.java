package com.Dual2024.ProjectCompetition.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.ModalityRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.TeamRepository;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.UserRepository;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        team = Team.builder().name("TestTeam").users(users1).modality(modality).captain(user).build();
        team2 = Team.builder().name("TestTeam2").users(users2).modality(modality).captain(user2).build();
        duplicatedNameModalityTeam = Team.builder().name("TestTeam").users(users2).captain(user3).modality(modality)
                .build();

    }

    @Test
    @DisplayName("findById operation")
    public void givenId_whenFindById_theReturnTeam() {

        Team savedTeam = teamRepository.save(team);

        Team foundTeam = teamRepository.findById(team.getId()).get();

        assertThat(foundTeam).isNotNull();
        assertThat(foundTeam).isEqualTo(savedTeam);
    }

    @Test
    @DisplayName("save operation")
    public void givenTeamObject_whenSave_theReturnSavedTeam() {

        Team savedTeam = teamRepository.save(team);

        assertThrows(DataIntegrityViolationException.class, () -> teamRepository.save(duplicatedNameModalityTeam));
        assertThat(savedTeam).isNotNull();
        assertThat(savedTeam.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenTeams_whenFindAll_thenReturnAllTeams() {

        teamRepository.save(team);
        teamRepository.save(team2);

        List<Team> teams = teamRepository.findAll();

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByName operation")
    public void givenTeam_whenFindByName_thenReturnTeam() {

        teamRepository.save(team);
        teamRepository.save(team2);

        List<Team> teams = teamRepository.findByName("TestTeam");

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("findByModality operation")
    public void givenModality_whenFindByModality_thenReturnTeams() {

        teamRepository.save(team);
        teamRepository.save(team2);

        List<Team> teams = teamRepository.findByModality(modalityRepository.findByName("modality1").get());

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByCaptain operation")
    public void givenModality_whenFindByCaptain_thenReturnTeams() {

        teamRepository.save(team);

        List<Team> teams = teamRepository.findByCaptain(userRepository.findByNick("test").get());

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("update operation")
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
    @DisplayName("delete operation")
    public void givenTeam_whenDeleteById_thenRemoveTeam() {

        teamRepository.save(team);
        teamRepository.deleteById(team.getId());

        assertThat(teamRepository.findById(team.getId())).isNotPresent();

    }

}
