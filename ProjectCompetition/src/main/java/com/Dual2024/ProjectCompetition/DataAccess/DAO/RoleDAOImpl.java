package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.RoleRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * RoleDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 */
@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role save(Role role) throws DataException {
        try {
            return roleRepository.save(role);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Role not saved", nre);
        }

    }


    @Override
    public Role findById(Long id) throws DataException {
        try {
            Optional<Role> role = roleRepository.findById(id);
            if (role.isPresent()) {
                return role.get();
            } else {
                throw new EntityNotFoundException("Role not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }


    @Override
    public List<Role> findAll() throws DataException {
        try {
            List<Role> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                throw new EntityNotFoundException("Roles not found");
            } else {
                return roles;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }


    @Override
    public void delete(Long id) throws DataException {
        try {
            roleRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Role not deleted", nre);
        }

    }


    @Override
    public Role findByName(String name) throws DataException {
        try {
            Role role = roleRepository.findByName(name);
            if (role == null) {
                throw new EntityNotFoundException("Role not found");
            } else {
                return role;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }
}
