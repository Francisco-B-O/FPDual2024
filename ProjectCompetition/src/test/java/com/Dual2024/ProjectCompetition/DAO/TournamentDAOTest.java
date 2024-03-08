package com.Dual2024.ProjectCompetition.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DAO.*;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.*;
import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private Modality savedModality;
    private Format savedFormat;
    private Tournament tournament, tournament2, duplicatedNameModalityTournament;

    @BeforeEach
    public void setup() throws DataException {
        User user = User.builder().email("test@email.com").nick("test").password("passwordTest").state(UserState.CONECTADO)
                .build();
        User user2 = User.builder().email("test2@email.com").nick("test2").password("passwordTest")
                .state(UserState.CONECTADO).build();
        User user3 = User.builder().email("test3@email.com").nick("test3").password("passwordTest")
                .state(UserState.CONECTADO).build();
        User user4 = User.builder().email("test4@email.com").nick("test4").password("passwordTest")
                .state(UserState.CONECTADO).build();
        List<User> users1 = new ArrayList<User>();
        users1.add(userDAO.save(user));
        users1.add(userDAO.save(user2));
        List<User> users2 = new ArrayList<User>();
        users2.add(userDAO.save(user3));
        users2.add(userDAO.save(user4));
        Modality modality = Modality.builder().name("modality1").numberPlayers(2).build();
        savedModality = modalityDAO.save(modality);
        Team team = Team.builder().name("TestTeam").users(users1).captain(user).modality(modality).build();
        Team team2 = Team.builder().name("TestTeam2").users(users2).captain(user2).modality(modality).build();
        List<Team> teams = new ArrayList<Team>();
        teams.add(teamDAO.save(team));
        teams.add(teamDAO.save(team2));
        Format format = Format.builder().name("torneo").build();
        savedFormat = formatDAO.save(format);
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
    public void givenId_whenFindById_theReturnTournament() throws DataException {

        Tournament savedTournament = tournamentDAO.save(tournament);

        Tournament foundTournament = tournamentDAO.findById(tournament.getId());

        assertThat(foundTournament).isNotNull();
        assertThat(foundTournament).isEqualTo(savedTournament);

    }

    @Test
    @DisplayName("save operation")
    public void givenTournament_whenSave_thenReturnSavedTournament() throws DataException {

        Tournament savedTournament = tournamentDAO.save(tournament);


        assertThrows(DataException.class, () -> tournamentDAO.save(duplicatedNameModalityTournament));
        assertThat(savedTournament).isNotNull();
        assertThat(savedTournament.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenTournaments_whenFindAll_thenReturnAllTournaments() throws DataException {

        tournamentDAO.save(tournament);
        tournamentDAO.save(tournament2);

        List<Tournament> tournaments = tournamentDAO.findAll();

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByName operation")
    public void givenTournamentName_whenFindByName_thenReturnTournament() throws DataException {

        tournamentDAO.save(tournament);
        tournamentDAO.save(tournament2);

        List<Tournament> tournaments = tournamentDAO.findByName("Torneo de futbol");

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("findByFormat operation")
    public void givenFormat_whenFindByFormat_thenReturnTournaments() throws DataException {

        tournamentDAO.save(tournament);

        List<Tournament> tournaments = tournamentDAO.findByFormat(savedFormat);

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findBySize operation")
    public void givenSize_whenFindBySize_thenReturnTournaments() throws DataException {

        tournamentDAO.save(tournament);

        List<Tournament> tournaments = tournamentDAO.findBySize(2);

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findByStartDate operation")
    public void givenStartDate_whenFindByStartDate_thenReturnTournaments() throws DataException {

        tournamentDAO.save(tournament);

        List<Tournament> tournaments = tournamentDAO.findByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findByEndDate operation")
    public void givenEndDate_whenFindByEndDate_thenReturnTournaments() throws DataException {

        tournamentDAO.save(tournament);

        List<Tournament> tournaments = tournamentDAO.findByEndDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0));

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findByState operation")
    public void givenState_whenFindByState_thenReturnTournaments() throws DataException {

        tournamentDAO.save(tournament);

        List<Tournament> tournaments = tournamentDAO.findByState(TournamentState.EN_JUEGO);

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findByModality operation")
    public void givenModality_whenFindByModality_thenReturnTournaments() throws DataException {

        tournamentDAO.save(tournament);

        List<Tournament> tournaments = tournamentDAO.findByModality(savedModality);

        assertThat(tournaments).isNotNull();
        assertThat(tournaments.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("update operation")
    public void givenTournament_whenUpdate_thenReturnUpdatedTournament() throws DataException {

        tournamentDAO.save(tournament);
        Tournament updatedTournament = new Tournament();
        updatedTournament.setId(tournament.getId());
        updatedTournament.setName("Torneo de fútbol updated");

        Tournament savedUpdatedTournament = tournamentDAO.save(updatedTournament);

        assertThat(savedUpdatedTournament).isNotNull();
        assertThat(savedUpdatedTournament).isEqualTo(updatedTournament);
    }

    @Test
    @DisplayName("delete operation")
    public void givenTeam_whenDelete_thenRemoveTeam() throws DataException {

        tournamentDAO.save(tournament);

        tournamentDAO.delete(tournament.getId());

        assertThrows(DataException.class, () -> tournamentDAO.findByName("Torneo de futbol"));

    }
}
