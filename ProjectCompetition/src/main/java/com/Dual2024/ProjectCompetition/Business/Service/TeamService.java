package com.Dual2024.ProjectCompetition.Business.Service;

import java.util.List;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.TeamBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBOAux;

public interface TeamService {
	TeamBO registerTeam(UserBOAux captain, TeamBO teamBO) throws BusinessException;

	TeamBO getTeamById(Long id) throws BusinessException;

	List<TeamBO> getAllteams() throws BusinessException;

	void deleteTeam(Long id) throws BusinessException;

	List<TeamBO> getTeamsByName(String name) throws BusinessException;

	List<TeamBO> getTeamsByModality(ModalityBO modalityBO) throws BusinessException;

	TeamBO updateTeam(TeamBO teamBO) throws BusinessException;

	TeamBO addPlayer(Long id, TeamBO teamBO) throws BusinessException;
}
