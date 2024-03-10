package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for performing CRUD operations on Format.
 *
 * <p>This interface extends the JpaRepository interface, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on Format entities. The methods declared in this interface are automatically implemented by Spring Data JPA.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Data access operation to find the format with the specified name
 * Optional<Format> format = formatRepository.findByName("Single Elimination");
 * }
 * </pre>
 *
 * <p>The interface includes a method to find the format with the specified name.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface FormatRepository extends JpaRepository<Format, Long> {

    /**
     * Finds the format with the specified name.
     *
     * @param name the name of the role
     * @return the format with the specified name
     */
    Optional<Format> findByName(String name);
}
