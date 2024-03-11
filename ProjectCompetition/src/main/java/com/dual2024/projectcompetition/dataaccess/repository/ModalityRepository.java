package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for performing CRUD operations on Modality.
 *
 * <p>This interface extends the JpaRepository interface, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on Modality entities. The methods declared in this interface are automatically implemented by Spring Data JPA.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Data access operation to find all modalities that can accommodate 10 players
 * List<Modality> modalities = modalityRepository.findByNumberPlayers(10);
 *
 * // Data access operation to find the modality with the specified name
 * Optional<Modality> modality = modalityRepository.findByName("Football");
 * }
 * </pre>
 *
 * <p>The interface includes methods to find modalities based on the number of players they can accommodate and by name.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface ModalityRepository extends JpaRepository<Modality, Long> {
    /**
     * Finds all modalities that can accommodate the specified number of players.
     *
     * @param numberPlayers the number of players
     * @return a list of modalities that can accommodate the specified number of players
     */
    List<Modality> findByNumberPlayers(int numberPlayers);

    /**
     * Finds the modality with the specified name.
     *
     * @param name the name of the modality
     * @return the modality with the specified name
     */
    Optional<Modality> findByName(String name);
}
