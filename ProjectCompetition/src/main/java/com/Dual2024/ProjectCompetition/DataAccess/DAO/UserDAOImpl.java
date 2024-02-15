package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.UserRepository;

import jakarta.validation.ConstraintViolationException;

@Component
public class UserDAOImpl implements UserDAO {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) throws DataException {
		try {
			return userRepository.save(user);
		} catch (DataIntegrityViolationException dive) {
			throw new DataException("User not saved", dive);
		} catch (NestedRuntimeException nre) {
			throw new DataException("User not saved", nre);
		} catch (ConstraintViolationException cve) {
			throw new DataException("User not saved", cve);
		}

	}

	@Override
	public User findById(Long id) throws DataException {
		try {
			Optional<User> user = userRepository.findById(id);
			if (user.isPresent()) {
				return user.get();
			} else {
				throw new DataException("User not found");
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("User not found", nre);
		}

	}

	@Override
	public List<User> findAll() throws DataException {
		try {
			return userRepository.findAll();
		} catch (NestedRuntimeException nre) {
			throw new DataException("Users not found", nre);
		}

	}

	@Override
	public void delete(User user) throws DataException {
		try {
			if (user.equals(null)) {
				throw new DataException("User not deleted");
			} else {
				userRepository.delete(user);
			}
			userRepository.delete(user);
		} catch (NestedRuntimeException nre) {
			throw new DataException("User not deleted", nre);
		}

	}

	@Override
	public User findByNick(String nick) throws DataException {
		try {
			User user = userRepository.findByNick(nick);
			if (user == null) {
				throw new DataException("User not found");
			} else {
				return user;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("User not found", nre);
		}
	}

	@Override
	public User findByEmail(String email) throws DataException {
		try {
			User user = userRepository.findByEmail(email);
			if (user == null) {
				throw new DataException("User not found");
			} else {
				return user;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("User not found", nre);
		}

	}

	@Override
	public List<User> findByState(UserState state) throws DataException {
		try {
			return userRepository.findByState(state);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Users not found", nre);
		}
	}

}
