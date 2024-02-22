package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.RoleRepository;

import jakarta.validation.ConstraintViolationException;

@Component
public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(Role role) throws DataException {
		try {
			return roleRepository.save(role);
		} catch (DataIntegrityViolationException dive) {
			throw new DataException("Role not saved", dive);
		} catch (NestedRuntimeException nre) {
			throw new DataException("Role not saved", nre);
		} catch (ConstraintViolationException cve) {
			throw new DataException("Role not saved", cve);
		}

	}

	@Override
	public Role findById(Long id) throws DataException {
		try {
			Optional<Role> role = roleRepository.findById(id);
			if (role.isPresent()) {
				return role.get();
			} else {
				throw new DataException("Role not found");
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Role not found", nre);
		}

	}

	@Override
	public List<Role> findAll() throws DataException {
		try {
			List<Role> roles = roleRepository.findAll();
			if (roles.isEmpty()) {
				throw new DataException("Roles not found");
			} else {
				return roles;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Roles not found", nre);
		}

	}

	@Override
	public void delete(Role role) throws DataException {
		try {
			if (role.equals(null)) {
				throw new DataException("Role not deleted");
			} else {
				roleRepository.delete(role);
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Role not deleted", nre);
		}

	}

	@Override
	public Role findByName(String name) throws DataException {
		try {
			Role role = roleRepository.findByName(name);
			if (role == null) {
				throw new DataException("Role not found");
			} else {
				return role;
			}
		} catch (NestedRuntimeException nre) {
			throw new DataException("Role not found", nre);
		}
	}
}
