package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
