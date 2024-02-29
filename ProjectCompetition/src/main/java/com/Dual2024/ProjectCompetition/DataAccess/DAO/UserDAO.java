package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

public interface UserDAO {
	User save(User user) throws DataException;

	User findById(Long id) throws DataException;

	List<User> findAll() throws DataException;

	void delete(Long id) throws DataException;

	User findByNick(String nick) throws DataException;

	User findByEmail(String email) throws DataException;

	List<User> findByState(UserState state) throws DataException;

	List<User> findByNickOrEmail(String nick, String email) throws DataException;

}
