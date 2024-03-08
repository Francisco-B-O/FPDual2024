package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
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

    /**
     * Implementation of the save method
     *
     * @param role Role to save
     * @return Saved role
     * @throws DataException
     */
    @Override
    public Role save(Role role) throws DataException {
        try {
            return roleRepository.save(role);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Role not saved", nre);
        }

    }

    /**
     * Implementation of the findByIdMethod
     *
     * @param id The id of the role you are looking for
     * @return The role found
     * @throws DataException
     */
    @Override
    public Role findById(Long id) throws DataException {
        try {
            Optional<Role> role = roleRepository.findById(id);
            if (role.isPresent()) {
                return role.get();
            } else {
                throw new NotFoundException("Role not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findAll method
     *
     * @return A list with all the roles
     * @throws DataException
     */
    @Override
    public List<Role> findAll() throws DataException {
        try {
            List<Role> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                throw new NotFoundException("Roles not found");
            } else {
                return roles;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the delete method
     *
     * @param id The id of the role to be deleted
     * @throws DataException
     */
    @Override
    public void delete(Long id) throws DataException {
        try {
            roleRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Role not deleted", nre);
        }

    }

    /**
     * Implementation of the findByName method
     *
     * @param name The name of the role you are looking for
     * @return The role found
     * @throws DataException
     */
    @Override
    public Role findByName(String name) throws DataException {
        try {
            Role role = roleRepository.findByName(name);
            if (role == null) {
                throw new NotFoundException("Role not found");
            } else {
                return role;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }
}
