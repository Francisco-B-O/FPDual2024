package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
