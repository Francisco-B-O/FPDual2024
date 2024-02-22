package com.Dual2024.ProjectCompetition.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
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

import com.Dual2024.ProjectCompetition.Business.BusinessException.ActiveTournamentException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameAndModalityException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.InvalidDateException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;
import com.Dual2024.ProjectCompetition.Business.Service.TournamentServiceImpl;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TeamDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TournamentDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest {
	private TournamentBO tournamentBO;
	private Tournament tournament;
	private List<TournamentBO> tournamentsBOList;
	private List<Tournament> tournamentsList;
	private UserBOAux userBOAux;
	private ModalityBO modalityBO;
	private FormatBO formatBO;
	private TeamBOAux teamBOAux;
	private List<TeamBOAux> teamsAux;
	private List<UserBOAux> usersAux;
	private User user;
	private List<User> users;
	private Team team;
	private Modality modality;
	private List<Team> teams;
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
		userBOAux = UserBOAux.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
				.state(UserState.CONECTADO).roles(roles).build();
		usersAux = new ArrayList<UserBOAux>();
		usersAux.add(userBOAux);
		modalityBO = ModalityBO.builder().id(1L).name("modality1").numberPlayers(2).build();
		formatBO = FormatBO.builder().id(1L).name("torneo").build();
		teamBOAux = TeamBOAux.builder().id(1L).name("TestTeam").users(usersAux).captain(userBOAux).modality(modalityBO)
				.build();
		teamsAux = new ArrayList<TeamBOAux>();
		teamsAux.add(teamBOAux);
		tournamentBO = TournamentBO.builder().id(1L).name("Torneo de futbol").size(2).description("El mejor futbol")
				.format(formatBO).startDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0))
				.endDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)).state(TournamentState.EN_JUEGO).modality(modalityBO)
				.teams(teamsAux).build();
		tournamentsList = new ArrayList<Tournament>();
		tournamentsBOList = new ArrayList<TournamentBO>();
		tournamentsBOList.add(tournamentBO);
		Role role = Role.builder().id(1L).name("test").description("Test role").build();
		List<Role> roles1 = new ArrayList<Role>();
		roles1.add(role);
		user = User.builder().id(1L).email("test@email.com").nick("test").password("passwordTest")
				.state(UserState.CONECTADO).roles(roles1).build();
		users = new ArrayList<User>();
		users.add(user);
		modality = Modality.builder().id(1L).name("modality1").numberPlayers(2).build();
		team = Team.builder().id(1L).name("TestTeam").captain(user).users(users).modality(modality).build();
		teams = new ArrayList<Team>();
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
	public void givenTournamentBO_whenTeamRegister_thenReturnTournamentBO() {

		BDDMockito.given(boToModelConverter.tournamentBOToModel(tournamentBO)).willReturn(tournament);
		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		try {
			BDDMockito.given(tournamentDAO.save(tournament)).willReturn(tournament);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(tournamentDAO.findByModality(modality)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		TournamentBO savedTournament = null;
		try {
			savedTournament = tournamentService.registerTournament(tournamentBO);
		} catch (BusinessException e) {
		}

		assertThat(savedTournament).isNotNull();
		assertThat(savedTournament).isEqualTo(tournamentBO);

	}

	@Test
	@DisplayName("registerTournament operation : incorrect case -> duplicated name + modality")
	public void givenTournamentBO_whenTeamRegister_thenThrowDuplicatedNameAndModalityException() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		try {
			BDDMockito.given(tournamentDAO.findByModality(modality)).willReturn(tournamentsList);
		} catch (DataException e) {
		}

		assertThrows(DuplicatedNameAndModalityException.class,
				() -> tournamentService.registerTournament(tournamentBO));

	}

	@Test
	@DisplayName("registerTournament operation : incorrect case -> duplicated name + modality")
	public void givenTournamentBO_whenTeamRegister_thenThrowInvalidDateException() {

		tournamentBO.setEndDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));
		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);

		try {
			BDDMockito.given(tournamentDAO.findByModality(modality)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(InvalidDateException.class, () -> tournamentService.registerTournament(tournamentBO));

	}

	@Test
	@DisplayName("getTournamentByID operation : correct case")
	public void givenId_whenGetTournamentByID_thenReturnTournamentBO() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);

		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
		} catch (DataException e) {
		}

		TournamentBO foundTournament = null;
		try {
			foundTournament = tournamentService.getTournamentById(1L);
		} catch (BusinessException e) {
		}

		assertThat(foundTournament).isNotNull();
		assertThat(foundTournament).isEqualTo(tournamentBO);

	}

	@Test
	@DisplayName("registerTournament operation : incorrect case -> not found")
	public void givenId_whenGetTournamentByID_thenThrowsBusinessException() {

		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.getTournamentById(1L));

	}

	@Test
	@DisplayName("getAllTournaments operation : correct case")
	public void givenNothing_whenGetAllTournaments_thenReturnAllTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findAll()).willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getAllTournaments();
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getAllTournaments operation : incorrect case -> not found")
	public void givenNothing_whenGetAllTournaments_thenThrowBusinessException() {

		try {
			BDDMockito.given(tournamentDAO.findAll()).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.getAllTournaments());

	}

	@Test
	@DisplayName("getTournamentsByName operation : correct case")
	public void givenNothing_whenGetTournamentsByName_thenReturnThisTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findByName("Torneo de futbol")).willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getTournamentsByName("Torneo de futbol");
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getTournamentsByName operation : incorrect case -> not found")
	public void givenNothing_whenGetTournamentsByName_thenThrowBusinessException() {

		try {
			BDDMockito.given(tournamentDAO.findByName("Torneo de futbol")).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.getTournamentsByName("Torneo de futbol"));

	}

	@Test
	@DisplayName("getTournamentsByFormat operation : correct case")
	public void givenNothing_whenGetTournamentsByFormat_thenReturnThisTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
		try {
			BDDMockito.given(tournamentDAO.findByFormat(format)).willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getTournamentsByFormat(formatBO);
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getTournamentsByFormat operation : incorrect case -> not found")
	public void givenNothing_whenGetTournamentsByFormat_thenThrowBusinessException() {

		BDDMockito.given(boToModelConverter.formatBOToModel(formatBO)).willReturn(format);
		try {
			BDDMockito.given(tournamentDAO.findByFormat(format)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.getTournamentsByFormat(formatBO));

	}

	@Test
	@DisplayName("getTournamentsByModality operation : correct case")
	public void givenNothing_whenGetTournamentsByModality_thenReturnThisTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);
		try {
			BDDMockito.given(tournamentDAO.findByModality(modality)).willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getTournamentsByModality(modalityBO);
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getTournamentsByModality operation : incorrect case -> not found")
	public void givenNothing_whenGetTournamentsByModality_thenThrowBusinessException() {
		BDDMockito.given(boToModelConverter.modalityBOToModel(modalityBO)).willReturn(modality);

		try {
			BDDMockito.given(tournamentDAO.findByModality(modality)).willThrow(DataException.class);
		} catch (DataException e) {
		}
		assertThrows(BusinessException.class, () -> tournamentService.getTournamentsByModality(modalityBO));

	}

	@Test
	@DisplayName("getTournamentsBySize operation : correct case")
	public void givenNothing_whenGetTournamentsBySize_thenReturnThisTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findBySize(2)).willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getTournamentsBySize(2);
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getTournamentsBySize operation : incorrect case -> not found")
	public void givenNothing_whenGetTournamentsBySize_thenThrowBusinessException() {

		try {
			BDDMockito.given(tournamentDAO.findBySize(2)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.getTournamentsBySize(2));

	}

	@Test
	@DisplayName("getTournamentsByState operation : correct case")
	public void givenNothing_whenGetTournamentsByState_thenReturnThisTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findByState(TournamentState.EN_JUEGO)).willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getTournamentsByState(TournamentState.EN_JUEGO);
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getTournamentsByState operation : incorrect case -> not found")
	public void givenNothing_whenGetTournamentsByState_thenThrowBusinessException() {

		try {
			BDDMockito.given(tournamentDAO.findByState(TournamentState.EN_JUEGO)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.getTournamentsByState(TournamentState.EN_JUEGO));

	}

	@Test
	@DisplayName("getTournamentsByEndDate operation : correct case")
	public void givenNothing_whenGetTournamentsByEndDate_thenReturnThisTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)))
					.willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getTournamentsByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0));
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getTournamentsByEndDate operation : incorrect case -> not found")
	public void givenNothing_whenGetTournamentsByEndDate_thenThrowBusinessException() {

		try {
			BDDMockito.given(tournamentDAO.findByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)))
					.willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class,
				() -> tournamentService.getTournamentsByEndDate(LocalDateTime.of(2023, 6, 30, 18, 0, 0)));

	}

	@Test
	@DisplayName("getTournamentsByEndDate operation : correct case")
	public void givenNothing_whenGetTournamentsByStartDate_thenReturnThisTournaments() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0)))
					.willReturn(tournamentsList);
		} catch (DataException e) {
		}

		List<TournamentBO> foundTournaments = null;
		try {
			foundTournaments = tournamentService.getTournamentsByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0));
		} catch (BusinessException e) {
		}

		assertThat(foundTournaments).isNotNull();
		assertThat(foundTournaments).isNotEmpty();
		assertThat(foundTournaments.getFirst()).isEqualTo(tournamentBO);
	}

	@Test
	@DisplayName("getTournamentsByStartDate operation : incorrect case -> not found")
	public void givenNothing_whenGetTournamentsByStartDate_thenThrowBusinessException() {

		try {
			BDDMockito.given(tournamentDAO.findByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0)))
					.willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class,
				() -> tournamentService.getTournamentsByStartDate(LocalDateTime.of(2022, 6, 1, 10, 0, 0)));

	}

	@Test
	@DisplayName("deleteTournament operation : correct case")
	public void givenId_whenDeleteTournament_thenDeleteTournament() {

		tournamentBO.setState(TournamentState.NO_COMENZADO);
		tournament.setState(TournamentState.NO_COMENZADO);
		BDDMockito.given(boToModelConverter.tournamentBOToModel(tournamentBO)).willReturn(tournament);
		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
		} catch (DataException e) {
		}
		try {
			BDDMockito.willDoNothing().given(tournamentDAO).delete(tournament);
		} catch (DataException e) {
		}
		try {
			tournamentService.deleteTournament(1L);
		} catch (BusinessException e) {
		}

		try {
			verify(tournamentDAO, times(1)).delete(tournament);
		} catch (DataException e) {
		}
	}

	@Test
	@DisplayName("deleteTournament operation : incorrect case -> active tournament")
	public void givenId_whenDeleteTournament_thenThrowActiveTournamentException() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
		} catch (DataException e) {
		}

		assertThrows(ActiveTournamentException.class, () -> tournamentService.deleteTournament(1L));
	}

	@Test
	@DisplayName("deleteTournament operation : incorrect case -> not found")
	public void givenIdThatNotExists_whenDeleteTournament_thenThrowBusinesException() {

		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willThrow(DataException.class);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.deleteTournament(1L));
	}

	@Test
	@DisplayName("addTeam operation : correct case")
	public void givenIdAndTournamentBO_whenAddTeam_thenReturnTournamentWithTeam() {

		tournament.setTeams(null);
		tournamentBO.setTeams(null);
		BDDMockito.given(boToModelConverter.tournamentBOToModel(tournamentBO)).willReturn(tournament);
		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		BDDMockito.given(modelToBOConverter.teamModelToBOAux(team)).willReturn(teamBOAux);
		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(tournamentDAO.save(tournament)).willReturn(tournament);
		} catch (DataException e) {
		}
		TournamentBO savedTournament = null;
		try {
			savedTournament = tournamentService.addTeam(1L, tournamentBO);
		} catch (BusinessException e) {
		}
		assertThat(savedTournament.getTeams().getFirst()).isEqualTo(teamBOAux);
	}

	@Test
	@DisplayName("addTeam operation : incorrect case -> This team is already on the tournament ")
	public void givenIdAndTournamentBO_whenAddTeam_thenThrowBusinessException() {

		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		BDDMockito.given(modelToBOConverter.teamModelToBOAux(team)).willReturn(teamBOAux);
		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findById(1L)).willReturn(team);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.addTeam(1L, tournamentBO));
	}

	@Test
	@DisplayName("addTeam operation : incorrect case -> A player on the team is already on another participating team ")
	public void givenIdTeamWithUserAlredyExistsInTournament_AndTournamentBO_whenAddTeam_thenThrowBusinessException() {
		TeamBOAux teamBOAux2 = TeamBOAux.builder().id(2L).name("TestTeam2").users(usersAux).captain(userBOAux)
				.modality(modalityBO).build();
		Team team2 = Team.builder().id(2L).name("TestTeam2").captain(user).users(users).modality(modality).build();
		BDDMockito.given(modelToBOConverter.tournamentModelToBO(tournament)).willReturn(tournamentBO);
		BDDMockito.given(modelToBOConverter.teamModelToBOAux(team2)).willReturn(teamBOAux2);
		try {
			BDDMockito.given(tournamentDAO.findById(1L)).willReturn(tournament);
		} catch (DataException e) {
		}
		try {
			BDDMockito.given(teamDAO.findById(2L)).willReturn(team2);
		} catch (DataException e) {
		}

		assertThrows(BusinessException.class, () -> tournamentService.addTeam(2L, tournamentBO));
	}
}
