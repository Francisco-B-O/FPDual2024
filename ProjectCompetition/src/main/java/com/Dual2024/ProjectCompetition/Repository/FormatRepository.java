package com.Dual2024.ProjectCompetition.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.Model.Format;

@Repository
public interface FormatRepository extends JpaRepository<Format, Long> {
	Format findByName(String name);

}
