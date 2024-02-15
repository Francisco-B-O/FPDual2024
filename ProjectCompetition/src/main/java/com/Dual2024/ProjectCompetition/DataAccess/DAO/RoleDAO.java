package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

public interface RoleDAO {
	Role save(Role role) throws DataException;

	Role findById(Long id) throws DataException;

	List<Role> findAll() throws DataException;

	void delete(Role role) throws DataException;

	Role findByName(String name) throws DataException;
}
