package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Team;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;

public interface TeamDAO {
	Team save(Team team) throws DataException;

	Team findById(Long id) throws DataException;

	List<Team> findAll() throws DataException;

	void delete(Team team) throws DataException;

	List<Team> findByName(String name) throws DataException;

	List<Team> findByModality(Modality modality) throws DataException;

	List<Team> findByCaptain(User captain) throws DataException;
}
