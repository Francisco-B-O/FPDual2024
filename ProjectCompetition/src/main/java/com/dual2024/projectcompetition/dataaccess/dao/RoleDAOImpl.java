package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.RoleRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 * @see RoleDAO
 */
@Slf4j
@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role save(Role role) throws DataException {
        try {
            Role savedRole = roleRepository.save(role);
            log.info("Role saved successfully with id: {}", savedRole.getId());
            return savedRole;
        } catch (NestedRuntimeException | ConstraintViolationException e) {
            log.error("Error saving role", e);
            throw new DataException("Role not saved", e);
        }
    }

    @Override
    public Role findById(Long id) throws DataException {
        try {
            Role role = roleRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Role not found"));
            log.info("Role found by id: {}", id);
            return role;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding role by id: {}", id, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Role> findAll() throws DataException {
        try {
            List<Role> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                log.warn("No roles found");
                throw new EntityNotFoundException("Roles not found");
            } else {
                log.info("Found {} roles", roles.size());
                return roles;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding all roles", nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public void delete(Long id) throws DataException {
        try {
            roleRepository.deleteById(id);
            log.info("Role deleted successfully with id: {}", id);
        } catch (NestedRuntimeException nre) {
            log.error("Error deleting role with id: {}", id, nre);
            throw new DataException("Role not deleted", nre);
        }
    }

    @Override
    public Role findByName(String name) throws DataException {
        try {
            Role role = roleRepository.findByName(name)
                    .orElseThrow(() -> new EntityNotFoundException("Role not found"));
            log.info("Role found by name: {}", name);
            return role;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding role by name: {}", name, nre);
            throw new DataException("Data access error", nre);
        }
    }
}
