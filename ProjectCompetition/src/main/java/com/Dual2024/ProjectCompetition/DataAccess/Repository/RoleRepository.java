package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for performing CRUD operations on Role.
 *
 * @author Francisco Balonero Olivera
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Finds the role with the specified name.
     *
     * @param name the name of the role
     * @return the role with the specified name
     **/
    Role findByName(String name);
}
