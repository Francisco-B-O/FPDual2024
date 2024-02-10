package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;

public interface FormatDAO {
	Format save(Format format) throws DataException;

	Optional<Format> findById(Long id) throws DataException;

	List<Format> findAll() throws DataException;

	void delete(Format format) throws DataException;

	Format findByName(String name) throws DataException;
}
