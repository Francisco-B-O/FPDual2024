package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;

import java.util.List;

/**
 * Service interface containing methods for managing modalities.
 *
 * <p>This interface declares methods for performing CRUD operations on modalities. It includes methods
 * to add a new modality, retrieve a modality by ID, retrieve all modalities, retrieve a modality by name,
 * delete a modality, retrieve modalities by the number of players, and update a modality.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Obtain an instance of ModalityService
 * ModalityService modalityService = // instantiate or inject the implementation
 *
 * // Add a new modality
 * ModalityBO newModality = ModalityBO.builder().name("New Modality").build();
 * ModalityBO addedModality = modalityService.addModality(newModality);
 *
 * // Retrieve a modality by ID
 * Long modalityId = 1L;
 * ModalityBO retrievedModality = modalityService.getModalityById(modalityId);
 *
 * // Retrieve all modalities
 * List<ModalityBO> allModalities = modalityService.getAllModalities();
 *
 * // Delete a modality by ID
 * Long modalityToDeleteId = 2L;
 * modalityService.deleteModality(modalityToDeleteId);
 *
 * // Retrieve a modality by name
 * String modalityName = "Sample Modality";
 * ModalityBO modalityByName = modalityService.getModalityByName(modalityName);
 *
 * // Retrieve modalities by the number of players
 * int numberOfPlayers = 4;
 * List<ModalityBO> modalitiesByPlayers = modalityService.getModalitiesByNumberPlayers(numberOfPlayers);
 *
 * // Update a modality
 * ModalityBO modalityToUpdate = // retrieve a modality or create a new one
 * modalityToUpdate.setName("Updated Modality");
 * ModalityBO updatedModality = modalityService.updateModality(modalityToUpdate);
 * }
 * </pre>
 *
 * <p>Implementing classes should handle {@link BusinessException} to appropriately manage exceptions
 * related to business operations on modalities.</p>
 *
 * <p>Instances of this interface are typically used in the business layer of the application to provide
 * a clean separation between the business logic and data access logic.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface ModalityService {
    /**
     * Adds a new modality.
     *
     * @param modalityBO The modality business object
     * @return The saved modality business object
     * @throws BusinessException If an error occurs during the operation
     */
    ModalityBO addModality(ModalityBO modalityBO) throws BusinessException;

    /**
     * Retrieves a modality by ID.
     *
     * @param id The ID of the modality
     * @return The modality business object
     * @throws BusinessException If the modality is not found or an error occurs during the operation
     */
    ModalityBO getModalityById(Long id) throws BusinessException;

    /**
     * Retrieves all modalities.
     *
     * @return A list of all modalities
     * @throws BusinessException If an error occurs during the operation
     */
    List<ModalityBO> getAllModalities() throws BusinessException;

    /**
     * Retrieves a modality by name.
     *
     * @param name The name of the modality
     * @return The modality business object
     * @throws BusinessException If the modality is not found or an error occurs during the operation
     */
    ModalityBO getModalityByName(String name) throws BusinessException;

    /**
     * Deletes a modality by ID.
     *
     * @param id The ID of the modality to be deleted
     * @throws BusinessException If the modality is not found or an error occurs during the operation
     */
    void deleteModality(Long id) throws BusinessException;

    /**
     * Retrieves modalities by the number of players.
     *
     * @param numberPlayers The number of players
     * @return A list of modalities that match the specified number of players
     * @throws BusinessException If an error occurs during the operation
     */
    List<ModalityBO> getModalitiesByNumberPlayers(int numberPlayers) throws BusinessException;

    /**
     * Updates a modality.
     *
     * @param modalityBO The updated modality business object
     * @return The updated modality business object
     * @throws BusinessException If the modality is not found or an error occurs during the operation
     */
    ModalityBO updateModality(ModalityBO modalityBO) throws BusinessException;
}
