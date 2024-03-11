package com.dual2024.projectcompetition.Service;

import com.dual2024.projectcompetition.dataaccess.dao.TeamDAO;
import com.dual2024.projectcompetition.dataaccess.dao.TournamentDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.*;
import com.dual2024.projectcompetition.utils.TournamentState;
import com.dual2024.projectcompetition.utils.UserState;
import com.dual2024.projectcompetition.business.businessexception.*;
import com.dual2024.projectcompetition.business.businessobject.*;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import com.dual2024.projectcompetition.business.service.TournamentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest {
    private TournamentBO tournamentBO;
    private Tournament tournament;
    private List<Tournament> tournamentsList;
    private ModalityBO modalityBO;
    private FormatBO formatBO;
    private TeamBO teamBOAux;
    private List<UserBOAux> usersAux;
    private User user;
    private List<User> users;
    private Team team;
    private Modality modality;
    private Format format;

    @Mock
    TeamDAO teamDAO;
    @Mock
    TournamentDAO tournamentDAO;
    @Mock
    ModelToBOConverter modelToBOConverter;
    @Mock
    BOToModelConverter boToModelConverter;
    @InjectMocks
    TournamentServiceImpl tournamentService;

    @BeforeEach
    public void setup() {
        RoleBO roleBO = RoleBO.builder().id(1L).name("test").description("JUGADOR").build();
        List<RoleBO> roles = new ArrayList<RoleBO>();
        roles.add(roleBO);
        UserBOAux userBOAux = UserBOAux.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
                .state(UserState.CONECTADO).roles(roles).build();
        usersAux = new ArrayList<UserBOAux>();
        usersAux.add(userBOAux);
        modalityBO = ModalityBO.builder().id(1L).name("modality1").numberPlayers(2).build();
        formatBO = FormatBO.builder().id(1L).name("torneo").build();
        teamBOAux = TeamBO.builder().id(1L).name("TestTeam").users(usersAux).modality(modalityBO).build();
        List<TeamBO> teamsAux = new ArrayList<TeamBO>();
        teamsAux.add(teamBOAux);
        tournamentBO = TournamentBO.builder().id(1L).name("Torneo de futbol").size(2).description("El mejor futbol")
                .format(formatBO).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)).state(TournamentState.EN_JUEGO).modality(modalityBO)
                .teams(teamsAux).build();
        tournamentsList = new ArrayList<Tournament>();
        Role role = Role.builder().id(1L).name("test").description("Test role").build();
        List<Role> roles1 = new ArrayList<Role>();
        roles1.add(role);
        user = User.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
                .state(UserState.CONECTADO).roles(roles1).build();
        users = new ArrayList<User>();
        users.add(user);
        modality = Modality.builder().id(1L).name("modality1").numberPlayers(2).build();
        team = Team.builder().id(1L).name("TestTeam").captain(user).users(users).modality(modality).build();
        List<Team> teams = new ArrayList<Team>();
        teams.add(team);
        user.setTeams(teams);
        format = Format.builder().id(1L).name("torneo").build();
        tournament = Tournament.builder().id(1L).name("Torneo de futbol").size(2).description("El mejor futbol")
                .format(format).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2022, 6, 30, 18, 0, 0)).state(TournamentState.EN_JUEGO).modality(modality)
                .teams(teams).build();
        tournamentsList.add(tournament);
    }

    @Test
    @DisplayName("registerTournament operation : correct case")
    public void givenTournamentBO_whenTeamRegister_thenReturnTournamentBO() throws DataException, BusinessException {

        BDDMockito.given(boToModelConverter.tournamentBOToModel(tournamentBO)).willReturn(tournament);
        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
        BDDMockito.given(tournamentDAO.save(tournament)).willReturn(tournament);
        BDDMockito.given(tournamentDAO.findByModality(modality)).willThrow(EntityNotFoundException.class);

        TournamentBO savedTournament = tournamentService.registerTournament(tournamentBO);

        assertThat(savedTournament).isNotNull();
        assertThat(savedTournament).isEqualTo(tournamentBO);

    }

    @Test
    @DisplayName("registerTournament operation : incorrect case -> duplicated name + modality")
    public void givenTournamentBO_whenTeamRegister_thenThrowDuplicatedNameAndModalityException() throws DataException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
        BDDMockito.given(tournamentDAO.findByModality(modality)).willReturn(tournamentsList);

        assertThrows(DuplicatedNameAndModalityException.class,
                () -> tournamentService.registerTournament(tournamentBO));

    }

    @Test
    @DisplayName("registerTournament operation : incorrect case -> invalid date")
    public void givenTournamentBO_whenTeamRegister_thenThrowInvalidDateException() throws DataException {

        tournamentBO.setEndDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));
        BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);

        BDDMockito.given(tournamentDAO.findByModality(modality)).willThrow(EntityNotFoundException.class);

        assertThrows(InvalidDateException.class, () -> tournamentService.registerTournament(tournamentBO));

    }

    @Test
    @DisplayName("getTournamentByID operation : correct case")
    public void givenId_whenGetTournamentByID_thenReturnTournamentBO() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);

        TournamentBO foundTournament = tournamentService.getTournamentById(1L);

        assertThat(foundTournament).isNotNull();
        assertThat(foundTournament).isEqualTo(tournamentBO);

    }

    @Test
    @DisplayName("getTournamentById operation : incorrect case -> not found")
    public void givenId_whenGetTournamentByID_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(tournamentDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getTournamentById(1L));

    }

    @Test
    @DisplayName("getAllTournaments operation : correct case")
    public void givenNothing_whenGetAllTournaments_thenReturnAllTournaments() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findAll()).willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getAllTournaments();

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getAllTournaments operation : incorrect case -> not found")
    public void givenNothing_whenGetAllTournaments_thenThrowBusinessException() throws DataException {

        BDDMockito.given(tournamentDAO.findAll()).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getAllTournaments());

    }

    @Test
    @DisplayName("getTournamentsByName operation : correct case")
    public void givenNothing_whenGetTournamentsByName_thenReturnThisTournaments() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findByName("Torneo de futbol")).willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getTournamentsByName("Torneo de futbol");

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getTournamentsByName operation : incorrect case -> not found")
    public void givenNothing_whenGetTournamentsByName_thenThrowBusinessException() throws DataException {

        BDDMockito.given(tournamentDAO.findByName("Torneo de futbol")).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class,
                () -> tournamentService.getTournamentsByName("Torneo de futbol"));

    }

    @Test
    @DisplayName("getTournamentsByFormat operation : correct case")
    public void givenNothing_whenGetTournamentsByFormat_thenReturnThisTournaments() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
        BDDMockito.given(tournamentDAO.findByFormat(format)).willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getTournamentsByFormat(formatBO);

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getTournamentsByFormat operation : incorrect case -> not found")
    public void givenNothing_whenGetTournamentsByFormat_thenThrowBusinessException() throws DataException {

        BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
        BDDMockito.given(tournamentDAO.findByFormat(format)).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getTournamentsByFormat(formatBO));

    }

    @Test
    @DisplayName("getTournamentsByModality operation : correct case")
    public void givenNothing_whenGetTournamentsByModality_thenReturnThisTournaments() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
        BDDMockito.given(tournamentDAO.findByModality(modality)).willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getTournamentsByModality(modalityBO);

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getTournamentsByModality operation : incorrect case -> not found")
    public void givenNothing_whenGetTournamentsByModality_thenThrowBusinessException() throws DataException {

        BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
        BDDMockito.given(tournamentDAO.findByModality(modality)).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getTournamentsByModality(modalityBO));

    }

    @Test
    @DisplayName("getTournamentsBySize operation : correct case")
    public void givenNothing_whenGetTournamentsBySize_thenReturnThisTournaments() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findBySize(2)).willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getTournamentsBySize(2);

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getTournamentsBySize operation : incorrect case -> not found")
    public void givenNothing_whenGetTournamentsBySize_thenThrowBusinessException() throws DataException {

        BDDMockito.given(tournamentDAO.findBySize(2)).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getTournamentsBySize(2));

    }

    @Test
    @DisplayName("getTournamentsByState operation : correct case")
    public void givenNothing_whenGetTournamentsByState_thenReturnThisTournaments() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findByState(TournamentState.EN_JUEGO)).willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getTournamentsByState(TournamentState.EN_JUEGO);

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getTournamentsByState operation : incorrect case -> not found")
    public void givenNothing_whenGetTournamentsByState_thenThrowBusinessException() throws DataException {

        BDDMockito.given(tournamentDAO.findByState(TournamentState.EN_JUEGO)).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class,
                () -> tournamentService.getTournamentsByState(TournamentState.EN_JUEGO));

    }

    @Test
    @DisplayName("getTournamentsByEndDate operation : correct case")
    public void givenNothing_whenGetTournamentsByEndDate_thenReturnThisTournaments() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)))
                .willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getTournamentsByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0));

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getTournamentsByEndDate operation : incorrect case -> not found")
    public void givenNothing_whenGetTournamentsByEndDate_thenThrowBusinessException() throws DataException {

        BDDMockito.given(tournamentDAO.findByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)))
                .willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class,
                () -> tournamentService.getTournamentsByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)));

    }

    @Test
    @DisplayName("getTournamentsByEndDate operation : correct case")
    public void givenNothing_whenGetTournamentsByStartDate_thenReturnThisTournaments() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0)))
                .willReturn(tournamentsList);

        List<TournamentBO> foundTournaments = tournamentService.getTournamentsByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));

        assertThat(foundTournaments).isNotNull();
        assertThat(foundTournaments).isNotEmpty();
        assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
    }

    @Test
    @DisplayName("getTournamentsByStartDate operation : incorrect case -> not found")
    public void givenNothing_whenGetTournamentsByStartDate_thenThrowBusinessException() throws DataException {

        BDDMockito.given(tournamentDAO.findByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0)))
                .willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class,
                () -> tournamentService.getTournamentsByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0)));

    }

    @Test
    @DisplayName("deleteTournament operation : correct case")
    public void givenId_whenDeleteTournament_thenDeleteTournament() throws BusinessException, DataException {

        tournamentBO.setState(TournamentState.NO_COMENZADO);
        tournament.setState(TournamentState.NO_COMENZADO);
        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
        BDDMockito.willDoNothing().given(tournamentDAO).delete(1L);

        tournamentService.deleteTournament(1L);

        verify(tournamentDAO, times(1)).delete(1L);

    }

    @Test
    @DisplayName("deleteTournament operation : incorrect case -> active tournament")
    public void givenId_whenDeleteTournament_thenThrowActiveTournamentException() throws DataException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);

        assertThrows(ActiveTournamentException.class, () -> tournamentService.deleteTournament(1L));
    }

    @Test
    @DisplayName("deleteTournament operation : incorrect case -> not found")
    public void givenIdThatNotExists_whenDeleteTournament_thenThrowBusinesException() throws DataException {

        BDDMockito.given(tournamentDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.deleteTournament(1L));
    }

    @Test
    @DisplayName("addTeam operation : correct case")
    public void givenIdAndTournamentBO_whenAddTeam_thenReturnTournamentWithTeam() throws DataException, BusinessException {

        tournament.setTeams(null);
        tournamentBO.setTeams(null);
        BDDMockito.given(boToModelConverter.tournamentBOToModel(tournamentBO)).willReturn(tournament);
        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBOAux);
        BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
        BDDMockito.given(tournamentDAO.save(tournament)).willReturn(tournament);

        TournamentBO savedTournament = tournamentService.addTeam(1L, tournamentBO.getId());

        assertThat(savedTournament.getTeams().getFirst()).isEqualTo(teamBOAux);
    }

    @Test
    @DisplayName("addTeam operation : incorrect case -> This team is already on the tournament ")
    public void givenIdAndTournamentBO_whenAddTeam_thenThrowBusinessException() throws DataException {

        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBOAux);
        BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);


        assertThrows(BusinessException.class, () -> tournamentService.addTeam(1L, tournamentBO.getId()));
    }

    @Test
    @DisplayName("addTeam operation : incorrect case -> A player on the team is already on another participating team ")
    public void givenIdTeamWithUserAlredyExistsInTournament_AndTournamentBO_whenAddTeam_thenThrowBusinessException() throws DataException {

        TeamBO teamBOAux2 = TeamBO.builder().id(2L).name("TestTeam2").users(usersAux).modality(modalityBO).build();
        Team team2 = Team.builder().id(2L).name("TestTeam2").captain(user).users(users).modality(modality).build();
        BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team2)).willReturn(teamBOAux2);
        BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
        BDDMockito.given(teamDAO.findById(2L)).willReturn(team2);

        assertThrows(BusinessException.class, () -> tournamentService.addTeam(2L, tournamentBO.getId()));
    }
}
