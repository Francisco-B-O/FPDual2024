package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

public interface ModalityDAO {
	Modality save(Modality modality) throws DataException;

	Modality findById(Long id) throws DataException;

	List<Modality> findAll() throws DataException;

	void delete(Modality modality) throws DataException;

	Modality findByName(String name) throws DataException;

	List<Modality> findByNumberPlayers(int numberPlayers) throws DataException;
}
