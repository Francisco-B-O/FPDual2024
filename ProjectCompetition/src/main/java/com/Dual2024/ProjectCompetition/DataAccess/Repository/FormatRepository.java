package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for performing CRUD operations on Format.
 *
 * @author Francisco Balonero Olivera
 */
public interface FormatRepository extends JpaRepository<Format, Long> {

    /**
     * Finds the format with the specified name.
     *
     * @param name the name of the role
     * @return the modality with the specified name
     */
    Format findByName(String name);

}
