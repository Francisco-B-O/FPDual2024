package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for performing CRUD operations on Modality.
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
     **/
    Modality findByName(String name);

}
