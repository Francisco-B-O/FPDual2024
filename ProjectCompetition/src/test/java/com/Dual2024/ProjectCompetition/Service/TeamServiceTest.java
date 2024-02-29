package com.Dual2024.ProjectCompetition.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedCaptainException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameAndModalityException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.FullTeamException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.TeamNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;
import com.Dual2024.ProjectCompetition.Business.Service.TeamServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TeamDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
	private Team team;
	private TeamBO teamBO;
	private UserBOAux userBOAux;
	private User user;
	private List<TeamBO> teamsBO;
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
		teamsBO = new ArrayList<TeamBO>();
		teamsBO.add(teamBO);
		teams.add(team);
	}

	@Test
	@DisplayName("teamRegister operation : correct case")
	public void givenCaptainAndTeamBO_whenTeamRegister_thenReturnTeamBO() {

		BDDMockito.given(boToModelConverter.userBOAuxToModel(userBOAux)).willReturn(user);
		BDDMockito.given(boToModelConverter.teamBOToModel(teamBO)).willReturn(team);
		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
		try {
			BDDMockito.given(teamDAO.save(team)).willReturn(team);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findByCaptain(user)).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findByModality(team.getModality())).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		TeamBO savedTeam = null;
		try {
			savedTeam = teamService.registerTeam(userBOAux, teamBO);
		} catch (BusinessException e) {
		}

		assertThat(savedTeam).isNotNull();
		assertThat(savedTeam).isEqualTo(teamBO);

	}

	@Test
	@DisplayName("teamRegister operation : incorrect case -> Duplicated name and modality")
	public void givenCaptainAndTeamBO_whenTeamRegister_thenThrowDuplicatedNameAndModalityException() {

		BDDMockito.given(boToModelConverter.userBOAuxToModel(userBOAux)).willReturn(user);
		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
		try {
			BDDMockito.given(teamDAO.findByCaptain(user)).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findByModality(team.getModality())).willReturn(teams);
		} catch (DataException e) {
		}

		assertThrows(DuplicatedNameAndModalityException.class, () -> teamService.registerTeam(userBOAux, teamBO));
	}

	@Test
	@DisplayName("teamRegister operation : incorrect case -> Duplicated captain")
	public void givenCaptainAndTeamBO_whenTeamRegister_thenThrowDuplicatedCaptainException() {

		BDDMockito.given(boToModelConverter.userBOAuxToModel(userBOAux)).willReturn(user);
		try {
			BDDMockito.given(teamDAO.findByCaptain(user)).willReturn(teams);
		} catch (DataException e) {
		}

		assertThrows(DuplicatedCaptainException.class, () -> teamService.registerTeam(userBOAux, teamBO));
	}

	@Test
	@DisplayName("getTeamById operation : correct case")
	public void givenId_whenGetTeamById_thenReturnTeamBO() {

		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {
		}

		TeamBO foundTeam = null;
		try {
			foundTeam = teamService.getTeamById(1L);
		} catch (BusinessException e) {
		}

		assertThat(foundTeam).isNotNull();
		assertThat(foundTeam).isEqualTo(teamBO);
	}

	@Test
	@DisplayName("getTeamById operation : incorrect case -> Not found")
	public void givenId_whenGetTeamById_thenThrowsBusinessException() {

		try {
			BDDMockito.given(teamDAO.findById(1L)).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}
		assertThrows(TeamNotFoundException.class, () -> teamService.getTeamById(1L));
	}

	@Test
	@DisplayName("getAllTeams operation : correct case")
	public void givenNothing_whenGetAllTeams_thenReturnAllTeams() {

		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		try {
			BDDMockito.given(teamDAO.findAll()).willReturn(teams);
		} catch (DataException e) {
		}

		List<TeamBO> foundTeams = null;
		try {
			foundTeams = teamService.getAllteams();
		} catch (BusinessException e) {
		}

		assertThat(foundTeams).isNotNull();
		assertThat(foundTeams).isNotEmpty();
		assertThat(foundTeams.getFirst()).isEqualTo(teamBO);
	}

	@Test
	@DisplayName("getAllTeams operation : incorrect case -> not found")
	public void givenNothing_whenGetAllTeams_thenThrowsBusinessException() {

		try {
			BDDMockito.given(teamDAO.findAll()).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(TeamNotFoundException.class, () -> teamService.getAllteams());

	}

	@Test
	@DisplayName("getTeamsByModality operation : correct case")
	public void givenModality_whenGetTeamsByModality_thenReturnThisTeams() {

		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
		try {
			BDDMockito.given(teamDAO.findByModality(team.getModality())).willReturn(teams);
		} catch (DataException e) {
		}

		List<TeamBO> foundTeams = null;
		try {
			foundTeams = teamService.getTeamsByModality(teamBO.getModality());
		} catch (BusinessException e) {
		}

		assertThat(foundTeams).isNotNull();
		assertThat(foundTeams).isNotEmpty();
		assertThat(foundTeams.getFirst()).isEqualTo(teamBO);
	}

	@Test
	@DisplayName("getTeamsByModality operation : incorrect case -> Not found")
	public void givenModality_whenGetTeamsByModality_thenThrowsBusinessException() {

		BDDMockito.given(boToModelConverter.modalityBOToModel(teamBO.getModality())).willReturn(team.getModality());
		try {
			BDDMockito.given(teamDAO.findByModality(team.getModality())).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(TeamNotFoundException.class, () -> teamService.getTeamsByModality(teamBO.getModality()));

	}

	@Test
	@DisplayName("getTeamsByName operation : correct case")
	public void givenModality_whenGetTeamsByName_thenReturnThisTeams() {

		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		try {
			BDDMockito.given(teamDAO.findByName(team.getName())).willReturn(teams);
		} catch (DataException e) {
		}

		List<TeamBO> foundTeams = null;
		try {
			foundTeams = teamService.getTeamsByName(teamBO.getName());
		} catch (BusinessException e) {
		}

		assertThat(foundTeams).isNotNull();
		assertThat(foundTeams).isNotEmpty();
		assertThat(foundTeams.getFirst()).isEqualTo(teamBO);
	}

	@Test
	@DisplayName("getTeamsByName operation : incorrect case -> Not found")
	public void givenModality_whenGetTeamsByName_thenThrowsBusinessException() {

		try {
			BDDMockito.given(teamDAO.findByName(team.getName())).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(TeamNotFoundException.class, () -> teamService.getTeamsByName(teamBO.getName()));

	}

	@Test
	@DisplayName("deleteTeam operation : correct case")
	public void givenId_whenDeleteTeam_thenDeleteTeam() {

		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {
		}
		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		try {
			BDDMockito.willDoNothing().given(teamDAO).delete(1L);
		} catch (DataException e) {
		}

		try {
			teamService.deleteTeam(1L);
		} catch (BusinessException e) {
		}

		try {
			verify(teamDAO, times(1)).delete(1L);
		} catch (DataException e) {
		}
	}

	@Test
	@DisplayName("deleteTeam operation : incorrect case -> Not found")
	public void givenId_whenDeleteTeam_thenThrowsBusinessException() {

		try {
			BDDMockito.given(teamDAO.findById(1L)).willThrow(NotFoundException.class);
		} catch (DataException e) {
		}

		assertThrows(TeamNotFoundException.class, () -> teamService.deleteTeam(1L));
	}

	@Test
	@DisplayName("deleteTeam operation : incorrect case -> Team in active tournament")
	public void givenIdTeamBOThatIsInActiveTournament_whenDeleteTeam_thenThrowsBusinessException() {
		teamBO.getTournaments().getFirst().setState(TournamentState.EN_JUEGO);
		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {

		}
		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);

		assertThrows(BusinessException.class, () -> teamService.deleteTeam(1L));
	}

	@Test
	@DisplayName("addPlayer operation : correct case")
	public void givenIdAndTeamBO_whenAddPlayer_thenReturnTeamWithPlayerAdded() {
		BDDMockito.given(boToModelConverter.teamBOToModel(teamBO)).willReturn(team);
		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {

		}
		try {
			teamService.addPlayer(1L, teamBO);
		} catch (BusinessException e) {
		}
		assertThat(teamBO.getUsers().getFirst()).isEqualTo(userBOAux);
	}

	@Test
	@DisplayName("addPlayer operation : incorrect case -> user is already on the team")
	public void givenIdThatExistsInHisTeamAndTeamBO_whenAddPlayer_thenThrowBusinessException() {

		List<User> users = new ArrayList<User>();
		List<UserBOAux> usersBO = new ArrayList<UserBOAux>();
		users.add(user);
		usersBO.add(userBOAux);
		team.setUsers(users);
		teamBO.setUsers(usersBO);
		BDDMockito.given(modelToBOConverter.teamModelToBO(team)).willReturn(teamBO);
		BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {

		}

		assertThrows(BusinessException.class, () -> teamService.addPlayer(1L, teamBO));
	}

	@Test
	@DisplayName("addPlayer operation : incorrect case -> full team")
	public void givenIdAndFullTeamBO_whenAddPlayer_thenThrowBusinessException() {

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
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {

		}

		assertThrows(FullTeamException.class, () -> teamService.addPlayer(1L, teamBO));
	}

	@Test
	@DisplayName("addPlayer operation : incorrect case -> Player not exists")
	public void givenIdThatNotExistsAndTeamBO_whenAddPlayer_thenThrowBusinessException() {

		try {
			BDDMockito.given(userDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(UserNotFoundException.class, () -> teamService.addPlayer(1L, teamBO));
	}

	@Test
	@DisplayName("addPlayer operation : incorrect case -> Team not exists")
	public void givenIdAndTeamBOThatNotExists_whenAddPlayer_thenThrowBusinessException() {

		BDDMockito.given(modelToBOConverter.userModelToBOAux(user)).willReturn(userBOAux);
		try {
			BDDMockito.given(userDAO.findById(1L)).willReturn(user);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(TeamNotFoundException.class, () -> teamService.addPlayer(1L, teamBO));
	}
}
