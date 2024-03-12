package com.dual2024.projectcompetition.BusinessObject;

import com.dual2024.projectcompetition.business.businessobject.*;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.dataaccess.model.*;
import com.dual2024.projectcompetition.utils.TournamentState;
import com.dual2024.projectcompetition.utils.UserState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.Business.BusinessObject")
public class BOToModelConverterTest {
    private static UserBO userBO;
    private static RoleBO roleBO;
    private static ModalityBO modalityBO;
    private static FormatBO formatBO;
    private static TeamBO teamBO;
    private static TournamentBO tournamentBO;
    private static UserBOAux userBOAux;
    private static TournamentBOAux tournamentBOAux;

    @Autowired
    private BOToModelConverter boToModelConverter;

    @BeforeAll
    public static void setup() {

        roleBO = RoleBO.builder().id(1L).name("test").description("Test role").build();
        List<RoleBO> roles = new ArrayList<RoleBO>();
        roles.add(roleBO);

        userBOAux = UserBOAux.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
                .state(UserState.CONNECTED).roles(roles).build();
        List<UserBOAux> usersAux = new ArrayList<UserBOAux>();
        usersAux.add(userBOAux);

        modalityBO = ModalityBO.builder().id(1L).name("modality1").numberPlayers(2).build();

        formatBO = FormatBO.builder().id(1L).name("torneo").build();

        tournamentBOAux = TournamentBOAux.builder().id(1L).name("Torneo de futbol").size(2)
                .description("El mejor futbol").format(formatBO).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0)).state(TournamentState.IN_GAME).modality(modalityBO)
                .build();
        List<TournamentBOAux> tournamentsAux = new ArrayList<TournamentBOAux>();
        tournamentsAux.add(tournamentBOAux);

        userBO = UserBO.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
                .state(UserState.CONNECTED).roles(roles).build();

        teamBO = TeamBO.builder().id(1L).name("TestTeam").users(usersAux).modality(modalityBO)
                .tournaments(tournamentsAux).build();
        List<TeamBO> teams = new ArrayList<TeamBO>();
        teams.add(teamBO);

        tournamentBO = TournamentBO.builder().id(1L).name("Torneo de futbol").size(2).description("El mejor futbol")
                .format(formatBO).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0)).state(TournamentState.IN_GAME).modality(modalityBO)
                .teams(teams).build();

    }

    @Test
    @DisplayName("UserBO -> User")
    public void givenUserBO_whenUserBOToModel_thenReturnUserModel() {

        User userTest = boToModelConverter.userBOToModel(userBO);

        assertThat(userTest.getId()).isEqualTo(userBO.getId());
        assertThat(userTest.getEmail()).isEqualTo(userBO.getEmail());
        assertThat(userTest.getNick()).isEqualTo(userBO.getNick());
        assertThat(userTest.getPassword()).isEqualTo(userBO.getPassword());
        assertThat(userTest.getState()).isEqualTo(UserState.CONNECTED);
        assertThat(userTest.getRoles().getFirst().getId()).isEqualTo(userBO.getRoles().getFirst().getId());
    }

    @Test
    @DisplayName("RoleBO -> Role")
    public void givenRoleBO_whenRoleBOToModel_thenReturnRoleModel() {

        Role roleTest = boToModelConverter.roleBOToModel(roleBO);
        assertThat(roleTest.getId()).isEqualTo(roleBO.getId());
        assertThat(roleTest.getName()).isEqualTo(roleBO.getName());
        assertThat(roleTest.getDescription()).isEqualTo(roleBO.getDescription());
    }

    @Test
    @DisplayName("ModalityBO -> Modality")
    public void givenModalityBO_whenModalityBOToModel_thenReturnModalityModel() {

        Modality modalityTest = boToModelConverter.modalityBOToModel(modalityBO);

        assertThat(modalityTest.getId()).isEqualTo(modalityBO.getId());
        assertThat(modalityTest.getName()).isEqualTo(modalityBO.getName());
        assertThat(modalityTest.getNumberPlayers()).isEqualTo(modalityBO.getNumberPlayers());
    }

    @Test
    @DisplayName("FormatBO -> Format")
    public void givenFormatBO_whenFormatBOToModel_thenReturnFormatModel() {

        Format formatTest = boToModelConverter.formatBOToModel(formatBO);

        assertThat(formatTest.getId()).isEqualTo(formatBO.getId());
        assertThat(formatTest.getName()).isEqualTo(formatBO.getName());
    }

    @Test
    @DisplayName("TournamentBO -> Tournament")
    public void givenTournamentBO_whenTournamentBOToModel_thenReturnTournamentModel() {

        Tournament tournamentTest = boToModelConverter.tournamentBOToModel(tournamentBO);

        assertThat(tournamentTest.getId()).isEqualTo(tournamentBO.getId());
        assertThat(tournamentTest.getDescription()).isEqualTo(tournamentBO.getDescription());
        assertThat(tournamentTest.getName()).isEqualTo(tournamentBO.getName());
        assertThat(tournamentTest.getStartDate()).isEqualTo(tournamentBO.getStartDate());
        assertThat(tournamentTest.getEndDate()).isEqualTo(tournamentBO.getEndDate());
        assertThat(tournamentTest.getState()).isEqualTo(TournamentState.IN_GAME);
        assertThat(tournamentTest.getSize()).isEqualTo(tournamentBO.getSize());
        assertThat(tournamentTest.getModality().getId()).isEqualTo(tournamentBO.getModality().getId());
        assertThat(tournamentTest.getFormat().getId()).isEqualTo(tournamentBO.getFormat().getId());
        assertThat(tournamentTest.getTeams().getFirst().getId()).isEqualTo(tournamentBO.getTeams().getFirst().getId());

    }

    @Test
    @DisplayName("TeamBO -> Team")
    public void givenTeamBO_whenTeamBOToModel_thenReturnTeamModel() {

        Team teamTest = boToModelConverter.teamBOToModel(teamBO);

        assertThat(teamTest.getId()).isEqualTo(teamBO.getId());
        assertThat(teamTest.getName()).isEqualTo(teamBO.getName());
        assertThat(teamTest.getUsers().getFirst().getId()).isEqualTo(teamBO.getUsers().getFirst().getId());
        assertThat(teamTest.getModality().getId()).isEqualTo(teamBO.getModality().getId());
    }

    @Test
    @DisplayName("UserBOAux -> User")
    public void givenUserBOAux_whenUserBOAuxToModel_thenReturnUserModel() {

        User userTest = boToModelConverter.userBOAuxToModel(userBOAux);

        assertThat(userTest.getId()).isEqualTo(userBOAux.getId());
        assertThat(userTest.getEmail()).isEqualTo(userBOAux.getEmail());
        assertThat(userTest.getNick()).isEqualTo(userBOAux.getNick());
        assertThat(userTest.getPassword()).isEqualTo(userBOAux.getPassword());
        assertThat(userTest.getState()).isEqualTo(UserState.CONNECTED);
        assertThat(userTest.getRoles().getFirst().getId()).isEqualTo(userBOAux.getRoles().getFirst().getId());
    }

    @Test
    @DisplayName("TournamentBOAux -> Tournament")
    public void givenTournamentBOAux_whenTournamentBOAuxToModel_thenReturnTournamentModel() {

        Tournament tournamentTest = boToModelConverter.tournamentBOAuxToModel(tournamentBOAux);

        assertThat(tournamentTest.getId()).isEqualTo(tournamentBOAux.getId());
        assertThat(tournamentTest.getDescription()).isEqualTo(tournamentBOAux.getDescription());
        assertThat(tournamentTest.getName()).isEqualTo(tournamentBOAux.getName());
        assertThat(tournamentTest.getStartDate()).isEqualTo(tournamentBOAux.getStartDate());
        assertThat(tournamentTest.getEndDate()).isEqualTo(tournamentBOAux.getEndDate());
        assertThat(tournamentTest.getState()).isEqualTo(TournamentState.IN_GAME);
        assertThat(tournamentTest.getSize()).isEqualTo(tournamentBOAux.getSize());
        assertThat(tournamentTest.getModality().getId()).isEqualTo(tournamentBOAux.getModality().getId());
        assertThat(tournamentTest.getFormat().getId()).isEqualTo(tournamentBOAux.getFormat().getId());

    }

}
