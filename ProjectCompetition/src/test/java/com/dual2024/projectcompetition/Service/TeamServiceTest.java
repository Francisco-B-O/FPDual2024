package com.dual2024.projectcompetition.Service;

import com.dual2024.projectcompetition.dataaccess.dao.TeamDAO;
import com.dual2024.projectcompetition.dataaccess.dao.UserDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Modality;
import com.dual2024.projectcompetition.dataaccess.model.Team;
import com.dual2024.projectcompetition.dataaccess.model.Tournament;
import com.dual2024.projectcompetition.dataaccess.model.User;
import com.dual2024.projectcompetition.utils.TournamentState;
import com.dual2024.projectcompetition.utils.UserState;
import com.dual2024.projectcompetition.business.businessexception.*;
import com.dual2024.projectcompetition.business.businessobject.ModalityBO;
import com.dual2024.projectcompetition.business.businessobject.TeamBO;
import com.dual2024.projectcompetition.business.businessobject.TournamentBOAux;
import com.dual2024.projectcompetition.business.businessobject.UserBOAux;
import com.dual2024.projectcompetition.business.businessobject.converters.BOToModelConverter;
import com.dual2024.projectcompetition.business.businessobject.converters.ModelToBOConverter;
import com.dual2024.projectcompetition.business.service.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    private Team team;
    private TeamBO teamBO;
    private UserBOAux userBOAux;
    private User user;
    private List<Team> teams;
    @Mock
    UserDAO userDAO;
    @Mock
    TeamDAO teamDAO;
    @Mock
    ModelToBOConverter modelToBOConverter;
    @Mock
    BOToModelConverter boToModelConverter;
    @InjectMocks
    TeamServiceImpl teamService;

    @BeforeEach
    public void setup() {
        userBOAux = UserBOAux.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
                .state(UserState.CONECTADO).build();
        user = User.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
                .state(UserState.CONECTADO).build();
        ModalityBO modalityBO = ModalityBO.builder().id(1L).name("modality1").numberPlayers(2).build();
        Modality modality = Modality.builder().id(1L).name("modality1").numberPlayers(2).build();
        Tournament tournament = Tournament.builder().state(TournamentState.NO_COMENZADO).build();
        List<Tournament> tournaments = new ArrayList<Tournament>();
        tournaments.add(tournament);
        TournamentBOAux tournamentBOAUX = TournamentBOAux.builder().state(TournamentState.NO_COMENZADO).build();
        List<TournamentBOAux> tournamentsBO = new ArrayList<TournamentBOAux>();
        tournamentsBO.add(tournamentBOAUX);
        team = Team.builder().modality(modality).id(1L).name("team1").logo("logo").captain(user)
                .tournaments(tournaments).build();
        teamBO = TeamBO.builder().modality(modalityBO).id(1L).name("team1").logo("logo").captainId(1L)
                .tournaments(tournamentsBO).build();
        teams = new ArrayList<Team>();
        teams.add(team);
    }

    @Test
    @DisplayName("teamRegister operation : correct case")
    public void givenCaptainAndTeamBO_whenTeamRegister_thenReturnTeamBO() throws BusinessException, DataException {

        BDDMockito.given(boToModelConverter.userBOAuxToModel(userBOAux)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
        BDDMockito.given(boToModelConverter.teamBOToModel(teamBO)).willReturn(team);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
        BDDMockito.given(teamDAO.save(team)).willReturn(team);
        BDDMockito.given(teamDAO.findByCaptain(user)).willThrow(EntityNotFoundException.class);
        BDDMockito.given(userDAO.findById(user.getId())).willReturn(user);
        BDDMockito.given(teamDAO.findByModality(team.getModality())).willThrow(EntityNotFoundException.class);

        TeamBO savedTeam = teamService.registerTeam(userBOAux.getId(), teamBO);

        assertThat(savedTeam).isNotNull();
        assertThat(savedTeam).isEqualTo(teamBO);

    }

    @Test
    @DisplayName("teamRegister operation : incorrect case -> Duplicated name and modality")
    public void givenCaptainAndTeamBO_whenTeamRegister_thenThrowDuplicatedNameAndModalityException() throws DataException {

        BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
        BDDMockito.given(boToModelConverter.userBOAuxToModel(userBOAux)).willReturn(user);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
        BDDMockito.given(userDAO.findById(user.getId())).willReturn(user);
        BDDMockito.given(teamDAO.findByCaptain(user)).willThrow(EntityNotFoundException.class);
        BDDMockito.given(teamDAO.findByModality(team.getModality())).willReturn(teams);

        assertThrows(DuplicatedNameAndModalityException.class,
                () -> teamService.registerTeam(userBOAux.getId(), teamBO));
    }

    @Test
    @DisplayName("teamRegister operation : incorrect case -> Duplicated captain")
    public void givenCaptainAndTeamBO_whenTeamRegister_thenThrowDuplicatedCaptainException() throws DataException {

        BDDMockito.given(boToModelConverter.userBOAuxToModel(userBOAux)).willReturn(user);
        BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
        BDDMockito.given(userDAO.findById(user.getId())).willReturn(user);
        BDDMockito.given(teamDAO.findByCaptain(user)).willReturn(teams);

        assertThrows(DuplicatedCaptainException.class, () -> teamService.registerTeam(userBOAux.getId(), teamBO));
    }

    @Test
    @DisplayName("getTeamById operation : correct case")
    public void givenId_whenGetTeamById_thenReturnTeamBO() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);

        TeamBO foundTeam = teamService.getTeamById(1L);

        assertThat(foundTeam).isNotNull();
        assertThat(foundTeam).isEqualTo(teamBO);
    }

    @Test
    @DisplayName("getTeamById operation : incorrect case -> Not found")
    public void givenId_whenGetTeamById_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(teamDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(TeamNotFoundException.class, () -> teamService.getTeamById(1L));
    }

    @Test
    @DisplayName("getAllTeams operation : correct case")
    public void givenNothing_whenGetAllTeams_thenReturnAllTeams() throws DataException, BusinessException {

        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(teamDAO.findAll()).willReturn(teams);

        List<TeamBO> foundTeams = teamService.getAllTeams();

        assertThat(foundTeams).isNotNull();
        assertThat(foundTeams).isNotEmpty();
        assertThat(foundTeams.getFirst()).isEqualTo(teamBO);
    }

    @Test
    @DisplayName("getAllTeams operation : incorrect case -> not found")
    public void givenNothing_whenGetAllTeams_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(teamDAO.findAll()).willThrow(EntityNotFoundException.class);

        assertThrows(TeamNotFoundException.class, () -> teamService.getAllTeams());

    }

    @Test
    @DisplayName("getTeamsByModality operation : correct case")
    public void givenModality_whenGetTeamsByModality_thenReturnThisTeams() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
        BDDMockito.given(teamDAO.findByModality(team.getModality())).willReturn(teams);

        List<TeamBO> foundTeams = teamService.getTeamsByModality(teamBO.getModality());

        assertThat(foundTeams).isNotNull();
        assertThat(foundTeams).isNotEmpty();
        assertThat(foundTeams.getFirst()).isEqualTo(teamBO);
    }

    @Test
    @DisplayName("getTeamsByModality operation : incorrect case -> Not found")
    public void givenModality_whenGetTeamsByModality_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
        BDDMockito.given(teamDAO.findByModality(team.getModality())).willThrow(EntityNotFoundException.class);

        assertThrows(TeamNotFoundException.class, () -> teamService.getTeamsByModality(teamBO.getModality()));

    }

    @Test
    @DisplayName("getTeamsByName operation : correct case")
    public void givenModality_whenGetTeamsByName_thenReturnThisTeams() throws BusinessException, DataException {

        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(teamDAO.findByName(team.getName())).willReturn(teams);

        List<TeamBO> foundTeams = teamService.getTeamsByName(teamBO.getName());

        assertThat(foundTeams).isNotNull();
        assertThat(foundTeams).isNotEmpty();
        assertThat(foundTeams.getFirst()).isEqualTo(teamBO);
    }

    @Test
    @DisplayName("getTeamsByName operation : incorrect case -> Not found")
    public void givenModality_whenGetTeamsByName_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(teamDAO.findByName(team.getName())).willThrow(EntityNotFoundException.class);

        assertThrows(TeamNotFoundException.class, () -> teamService.getTeamsByName(teamBO.getName()));

    }

    @Test
    @DisplayName("deleteTeam operation : correct case")
    public void givenId_whenDeleteTeam_thenDeleteTeam() throws BusinessException, DataException {

        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.willDoNothing().given(teamDAO).delete(1L);

        teamService.deleteTeam(1L);

        verify(teamDAO, times(1)).delete(1L);
    }

    @Test
    @DisplayName("deleteTeam operation : incorrect case -> Not found")
    public void givenId_whenDeleteTeam_thenThrowsBusinessException() throws DataException {

        BDDMockito.given(teamDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(TeamNotFoundException.class, () -> teamService.deleteTeam(1L));
    }

    @Test
    @DisplayName("deleteTeam operation : incorrect case -> Team in active tournament")
    public void givenIdTeamBOThatIsInActiveTournament_whenDeleteTeam_thenThrowsBusinessException() throws DataException {

        teamBO.getTournaments().getFirst().setState(TournamentState.EN_JUEGO);
        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);

        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);

        assertThrows(BusinessException.class, () -> teamService.deleteTeam(1L));
    }

    @Test
    @DisplayName("addPlayer operation : correct case")
    public void givenIdAndTeamBO_whenAddPlayer_thenReturnTeamWithPlayerAdded() throws DataException, BusinessException {

        BDDMockito.given(boToModelConverter.teamBOToModel(teamBO)).willReturn(team);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);

        teamService.addPlayer(1L, teamBO.getId());

        assertThat(teamBO.getUsers().getFirst()).isEqualTo(userBOAux);
    }

    @Test
    @DisplayName("addPlayer operation : incorrect case -> user is already on the team")
    public void givenIdThatExistsInHisTeamAndTeamBO_whenAddPlayer_thenThrowBusinessException() throws DataException {

        List<User> users = new ArrayList<User>();
        List<UserBOAux> usersBO = new ArrayList<UserBOAux>();
        users.add(user);
        usersBO.add(userBOAux);
        team.setUsers(users);
        teamBO.setUsers(usersBO);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);

        assertThrows(BusinessException.class, () -> teamService.addPlayer(1L, teamBO.getId()));
    }

    @Test
    @DisplayName("addPlayer operation : incorrect case -> full team")
    public void givenIdAndFullTeamBO_whenAddPlayer_thenThrowBusinessException() throws DataException {

        List<User> users = new ArrayList<User>();
        List<UserBOAux> usersBO = new ArrayList<UserBOAux>();
        users.add(user);
        usersBO.add(userBOAux);
        team.setUsers(users);
        teamBO.setUsers(usersBO);
        teamBO.getModality().setNumberPlayers(1);
        team.getModality().setNumberPlayers(1);
        BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
        BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(teamDAO.findById(1L)).willReturn(team);

        assertThrows(FullTeamException.class, () -> teamService.addPlayer(1L, teamBO.getId()));
    }

    @Test
    @DisplayName("addPlayer operation : incorrect case -> Player not exists")
    public void givenIdThatNotExistsAndTeamBO_whenAddPlayer_thenThrowBusinessException() throws DataException {

        BDDMockito.given(userDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> teamService.addPlayer(1L, teamBO.getId()));
    }

    @Test
    @DisplayName("addPlayer operation : incorrect case -> Team not exists")
    public void givenIdAndTeamBOThatNotExists_whenAddPlayer_thenThrowBusinessException() throws DataException {

        BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
        BDDMockito.given(userDAO.findById(1L)).willReturn(user);
        BDDMockito.given(teamDAO.findById(1L)).willThrow(EntityNotFoundException.class);

        assertThrows(TeamNotFoundException.class, () -> teamService.addPlayer(1L, teamBO.getId()));
    }
}
