package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Tournament;
import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;

public interface TournamentDAO {
	Tournament save(Tournament tournament) throws DataException;

	Optional<Tournament> findById(Long id) throws DataException;

	List<Tournament> findAll() throws DataException;

	void delete(Tournament tournament) throws DataException;

	Tournament findByName(String name) throws DataException;

	List<Tournament> findByFormat(Format format) throws DataException;

	List<Tournament> findBySize(int size) throws DataException;

	List<Tournament> findByStartDate(LocalDateTime startDate) throws DataException;

	List<Tournament> findByEndDate(LocalDateTime endDate) throws DataException;

	List<Tournament> findByState(TournamentState state) throws DataException;

	List<Tournament> findByModality(Modality modality) throws DataException;

}
