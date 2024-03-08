package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;

import java.util.List;

/**
 * Interface that contains the methods of the modality service.
 *
 * @author Francisco Balonero Olivera
 */
public interface ModalityService {
    /**
     * Add modality.
     *
     * @param modalityBO the modality bo
     * @return The saved modality bo
     * @throws BusinessException
     */
    ModalityBO addModality(ModalityBO modalityBO) throws BusinessException;

    /**
     * Gets modality by id.
     *
     * @param id the id
     * @return The modality by id
     * @throws BusinessException
     */
    ModalityBO getModalityById(Long id) throws BusinessException;

    /**
     * Gets all modalities.
     *
     * @return All modalities
     * @throws BusinessException
     */
    List<ModalityBO> getAllModalities() throws BusinessException;

    /**
     * Gets modality by name.
     *
     * @param name The name
     * @return The modality by name
     * @throws BusinessException
     */
    ModalityBO getModalityByName(String name) throws BusinessException;

    /**
     * Delete modality.
     *
     * @param id The id
     * @throws BusinessException
     */
    void deleteModality(Long id) throws BusinessException;

    /**
     * Gets modalities by number players.
     *
     * @param numberPlayers The number players
     * @return The modalities by number players
     * @throws BusinessException
     */
    List<ModalityBO> getModalitiesByNumberPlayers(int numberPlayers) throws BusinessException;

    /**
     * Update modality.
     *
     * @param modalityBO The modality bo
     * @return The updated modality bo
     * @throws BusinessException
     */
    ModalityBO updateModality(ModalityBO modalityBO) throws BusinessException;
}
