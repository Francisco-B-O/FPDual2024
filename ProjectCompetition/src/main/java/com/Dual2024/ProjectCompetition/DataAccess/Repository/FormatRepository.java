package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormatRepository extends JpaRepository<Format, Long> {
    Format findByName(String name);

}
