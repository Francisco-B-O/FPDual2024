package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

public interface RoleDAO {
	Role save(Role role) throws DataException;

	Optional<Role> findById(Long id) throws DataException;

	List<Role> findAll() throws DataException;

	void delete(Role Role) throws DataException;

	Role findByName(String name) throws DataException;
}
