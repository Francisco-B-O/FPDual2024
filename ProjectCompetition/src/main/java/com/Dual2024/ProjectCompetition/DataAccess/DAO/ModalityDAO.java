package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

import java.util.List;

/**
 * Interface containing the ModalityDAO methods
 *
 * @author Francisco Balonero Olivera
 */
public interface ModalityDAO {
    /**
     * Method that saves a modality
     *
     * @param modality Modality to save
     * @return Saved modality
     * @throws DataException the data exception
     */
    Modality save(Modality modality) throws DataException;

    /**
     * Method that searches a modality by id
     *
     * @param id The id of the modality you are looking for
     * @return The modality found
     * @throws DataException the data exception
     */
    Modality findById(Long id) throws DataException;

    /**
     * Method that returns a list with all the modalities
     *
     * @return A list with all the modalities
     * @throws DataException the data exception
     */
    List<Modality> findAll() throws DataException;

    /**
     * Method that deletes a modality by id
     *
     * @param id The id of the modality to be deleted
     * @throws DataException the data exception
     */
    void delete(Long id) throws DataException;

    /**
     * Method that searches a modality by name
     *
     * @param name The name of the modality you are looking for
     * @return The modality found
     * @throws DataException the data exception
     */
    Modality findByName(String name) throws DataException;

    /**
     * Method that searches a list with modalities with this number of player
     *
     * @param numberPlayers The number players of the modality you are looking for
     * @return A list with found modalities
     * @throws DataException the data exception
     */
    List<Modality> findByNumberPlayers(int numberPlayers) throws DataException;
}
