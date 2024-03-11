package com.dual2024.projectcompetition.dataaccess.repository;

import com.dual2024.projectcompetition.dataaccess.model.Modality;
import com.dual2024.projectcompetition.dataaccess.model.Team;
import com.dual2024.projectcompetition.dataaccess.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for performing CRUD operations on Team.
 *
 * <p>This interface extends the JpaRepository interface, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on Team entities. The methods declared in this interface are automatically implemented by Spring Data JPA.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Data access operation to find all teams with the specified name
 * List<Team> teamsByName = teamRepository.findByName("TeamName");
 *
 * // Data access operation to find all teams with the specified modality
 * List<Team> teamsByModality = teamRepository.findByModality(modality);
 *
 * // Data access operation to find all teams with the specified captain
 * List<Team> teamsByCaptain = teamRepository.findByCaptain(captain);
 * }
 * </pre>
 *
 * <p>The interface includes methods to find teams by name, modality, and captain.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * Finds all teams with the specified name.
     *
     * @param name the name of the team
     * @return a list of teams with the specified name
     */
    List<Team> findByName(String name);

    /**
     * Finds all teams with the specified modality.
     *
     * @param modality the modality of the team
     * @return a list of teams with the specified modality
     */
    List<Team> findByModality(Modality modality);

    /**
     * Finds all teams with the specified captain.
     *
     * @param captain the captain of the team
     * @return a list of teams with the specified captain
     */
    List<Team> findByCaptain(User captain);

}
