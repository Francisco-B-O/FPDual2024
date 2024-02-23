package com.Dual2024.ProjectCompetition.Business.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dual2024.ProjectCompetition.Business.BusinessException.ActiveTournamentException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameAndModalityException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.InvalidDateException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.TeamNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.TournamentNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.BOToModelConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBOAux;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TeamDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.TournamentDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {
	@Autowired
	TournamentDAO tournamentDAO;

	@Autowired
	TeamDAO teamDAO;

	@Autowired
	BOToModelConverter boToModelConverter;

	@Autowired
	ModelToBOConverter modelToBOConverter;

	@Override
	public TournamentBO registerTournament(TournamentBO tournamentBO) throws BusinessException {
		List<TournamentBO> listTournamentsBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findByModality(boToModelConverter.modalityBOToModel(tournamentBO.getModality()))
					.forEach((Tournament tournament) -> listTournamentsBO
							.add(modelToBOConverter.tournamentModelToBO(tournament)));
			for (TournamentBO foundTournament : listTournamentsBO) {
				if (foundTournament.getName().equals(tournamentBO.getName())) {
					throw new DuplicatedNameAndModalityException("The combination of name+modality already exists");
				}
			}
		} catch (DataException e) {
		}
		if (tournamentBO.getStartDate().isEqual(tournamentBO.getEndDate())
				|| tournamentBO.getStartDate().isAfter(tournamentBO.getEndDate())) {
			throw new InvalidDateException("Invalid dates");
		} else {
			try {
				return modelToBOConverter
						.tournamentModelToBO(tournamentDAO.save(boToModelConverter.tournamentBOToModel(tournamentBO)));
			} catch (DataException e) {
				throw new BusinessException("Error registering tournament", e);
			}
		}
	}

	@Override
	public TournamentBO getTournamentById(Long id) throws BusinessException {
		try {
			return modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(id));
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournament not found", e);
		}
	}

	@Override
	public List<TournamentBO> getAllTournaments() throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findAll().forEach((Tournament tournament) -> listTournamentBO
					.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public void deleteTournament(Long id) throws BusinessException {
		TournamentBO tournamentBO;
		try {
			tournamentBO = modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(id));
		} catch (DataException e) {
			throw new TournamentNotFoundException("tournament not found", e);
		}
		if (tournamentBO.getState().equals(TournamentState.EN_JUEGO)) {
			throw new ActiveTournamentException("This tournament is active");
		} else {
			try {
				tournamentDAO.delete(boToModelConverter.tournamentBOToModel(tournamentBO));
			} catch (DataException e) {
				throw new BusinessException("Tournament not deleted", e);
			}
		}
	}

	@Override
	public List<TournamentBO> getTournamentsByName(String name) throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findByName(name).forEach((Tournament tournament) -> listTournamentBO
					.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public List<TournamentBO> getTournamentsByFormat(FormatBO formatBO) throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findByFormat(boToModelConverter.formatBOToModel(formatBO))
					.forEach((Tournament tournament) -> listTournamentBO
							.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public List<TournamentBO> getTournamentsBySize(int size) throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findBySize(size).forEach((Tournament tournament) -> listTournamentBO
					.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public List<TournamentBO> getTournamentsByStartDate(LocalDateTime startDate) throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findByStartDate(startDate).forEach((Tournament tournament) -> listTournamentBO
					.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public List<TournamentBO> getTournamentsByEndDate(LocalDateTime endDate) throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findByEndDate(endDate).forEach((Tournament tournament) -> listTournamentBO
					.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public List<TournamentBO> getTournamentsByState(TournamentState state) throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findByState(state).forEach((Tournament tournament) -> listTournamentBO
					.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public List<TournamentBO> getTournamentsByModality(ModalityBO modalityBO) throws BusinessException {
		List<TournamentBO> listTournamentBO = new ArrayList<TournamentBO>();
		try {
			tournamentDAO.findByModality(boToModelConverter.modalityBOToModel(modalityBO))
					.forEach((Tournament tournament) -> listTournamentBO
							.add(modelToBOConverter.tournamentModelToBO(tournament)));
			return listTournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("Tournaments not found", e);
		}
	}

	@Override
	public TournamentBO updateTournament(TournamentBO tournamentBO) throws BusinessException {
		TournamentBO tournament = null;
		try {
			tournament = modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(tournamentBO.getId()));
			tournament = tournamentBO;
		} catch (DataException e) {
			throw new TournamentNotFoundException("This tournament not exists", e);
		}
		try {

			return modelToBOConverter
					.tournamentModelToBO(tournamentDAO.save(boToModelConverter.tournamentBOToModel(tournament)));
		} catch (DataException e) {
			throw new BusinessException("Tournament could not be updated", e);
		}
	}

	@Override
	public TournamentBO addTeam(Long id, TournamentBO tournamentBO) throws BusinessException {
		TeamBOAux team = null;

		try {
			team = modelToBOConverter.teamModelToBOAux(teamDAO.findById(id));
		} catch (DataException e) {
			throw new TeamNotFoundException("This team not exists", e);
		}
		TournamentBO tournament = null;
		try {
			tournament = modelToBOConverter.tournamentModelToBO(tournamentDAO.findById(tournamentBO.getId()));
			List<TeamBOAux> teams = new ArrayList<TeamBOAux>();
			if (tournament.getTeams() == null) {
				teams.add(team);
				tournament.setTeams(teams);
			} else if (tournament.getTeams().size() >= tournamentBO.getSize()) {
				throw new BusinessException("Full tournament  ");
			} else if (tournament.getTeams().contains(team)) {
				throw new BusinessException("This team is already on the tournament ");
			} else {
				teams = tournament.getTeams();
				for (TeamBOAux t : teams) {
					for (UserBOAux u : team.getUsers()) {
						if (t.getUsers().contains(u)) {
							throw new BusinessException(
									"A player on the team is already on another participating team ");
						}
					}
				}
				teams.add(team);
				tournament.setTeams(teams);
			}

		} catch (DataException e) {
			throw new TournamentNotFoundException("This tournament not exists", e);
		}
		try {

			return modelToBOConverter
					.tournamentModelToBO(tournamentDAO.save(boToModelConverter.tournamentBOToModel(tournament)));
		} catch (DataException e) {
			throw new BusinessException("Team could not be added", e);
		}
	}
}
