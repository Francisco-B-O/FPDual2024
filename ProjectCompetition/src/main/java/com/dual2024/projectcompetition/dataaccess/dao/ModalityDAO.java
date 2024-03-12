package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.Modality;

import java.util.List;

/**
 * Interface containing the ModalityDAO methods.
 * This interface provides methods for interacting with modality data in the data access layer.
 * Implementations of this interface handle the storage, retrieval, and deletion of modality entities.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation to save a modality
 *     Modality savedModality = modalityDAO.save(newModality);
 *
 *     // Data access operation to find a modality by ID
 *     Modality foundModality = modalityDAO.findById(modalityId);
 *
 *     // Data access operation to retrieve a list of all modalities
 *     List<Modality> allModalities = modalityDAO.findAll();
 *
 *     // Data access operation to delete a modality by ID
 *     modalityDAO.delete(modalityId);
 *
 *     // Data access operation to find a modality by name
 *     Modality modalityByName = modalityDAO.findByName("modalityName");
 *
 *     // Data access operation to find a list of modalities by number of players
 *     List<Modality> modalitiesByNumberPlayers = modalityDAO.findByNumberPlayers(10);
 * } catch (DataException e) {
 *     // Handle the exception
 *     logger.error("Data access error: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The interface defines methods for saving, finding by ID, finding all, deleting by ID, finding by name,
 * and finding by number of players.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface ModalityDAO {
    /**
     * Saves a modality.
     *
     * @param modality {@link Modality} Modality to save.
     * @return {@link Modality} Saved modality.
     * @throws DataException If there is an issue saving the modality.
     */
    Modality save(Modality modality) throws DataException;

    /**
     * Finds a modality by its ID.
     *
     * @param id {@link Long} The ID of the modality to find.
     * @return {@link Modality} The modality found.
     * @throws DataException If there is an issue retrieving the modality.
     */
    Modality findById(Long id) throws DataException;

    /**
     * Retrieves a list of all modalities.
     *
     * @return {@link List} A list with all the modalities.
     * @throws DataException If there is an issue retrieving the modalities.
     */
    List<Modality> findAll() throws DataException;

    /**
     * Deletes a modality by its ID.
     *
     * @param id {@link Long}The ID of the modality to be deleted.
     * @throws DataException If there is an issue deleting the modality.
     */
    void delete(Long id) throws DataException;

    /**
     * Finds a modality by its name.
     *
     * @param name {@link String} The name of the modality to find.
     * @return {@link Modality} The modality found.
     * @throws DataException If there is an issue retrieving the modality by name.
     */
    Modality findByName(String name) throws DataException;

    /**
     * Finds a list of modalities with a specified number of players.
     *
     * @param numberPlayers {@link Integer} The number of players of the modalities to find.
     * @return @{@link List} A list with found modalities.
     * @throws DataException If there is an issue retrieving the modalities.
     */
    List<Modality> findByNumberPlayers(int numberPlayers) throws DataException;
}
