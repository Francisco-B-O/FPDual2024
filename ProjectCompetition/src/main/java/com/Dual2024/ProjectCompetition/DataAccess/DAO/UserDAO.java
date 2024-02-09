package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

public interface UserDAO {
	User save(User user) throws DataException;

	Optional<User> findById(Long id) throws DataException;

	List<User> findAll() throws DataException;

	void delete(User user) throws DataException;

	User findByNick(String nick) throws DataException;

	User findByEmail(String email) throws DataException;

	List<User> findByState(UserState state) throws DataException;

}
