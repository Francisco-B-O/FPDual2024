package com.Dual2024.ProjectCompetition.Business.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TournamentBO;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;

public interface TournamentService {
	TournamentBO registerTournament(TournamentBO tournamentBO) throws BusinessException;

	TournamentBO getTournamentById(Long id) throws BusinessException;

	List<TournamentBO> getAllTournaments() throws BusinessException;

	void deleteTournament(Long id) throws BusinessException;

	List<TournamentBO> getTournamentsByName(String name) throws BusinessException;

	List<TournamentBO> getTournamentsByFormat(FormatBO formatBO) throws BusinessException;

	List<TournamentBO> getTournamentsBySize(int size) throws BusinessException;

	List<TournamentBO> getTournamentsByStartDate(LocalDateTime startDate) throws BusinessException;

	List<TournamentBO> getTournamentsByEndDate(LocalDateTime endDate) throws BusinessException;

	List<TournamentBO> getTournamentsByState(TournamentState state) throws BusinessException;

	List<TournamentBO> getTournamentsByModality(ModalityBO modalityBO) throws BusinessException;

	TournamentBO updateTournament(TournamentBO tournamentBO) throws BusinessException;

	TournamentBO addTeam(Long id, TournamentBO tournamentBO) throws BusinessException;
}
