package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.ModalityRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ModalityDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 */
@Repository
public class ModalityDAOImpl implements ModalityDAO {
    @Autowired
    private ModalityRepository modalityRepository;

    /**
     * Implementation of the save method
     *
     * @param modality Modality to save
     * @return Saved modality
     * @throws DataException
     */
    @Override
    public Modality save(Modality modality) throws DataException {
        try {
            return modalityRepository.save(modality);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Modality not saved", nre);
        }

    }

    /**
     * Implementation of the findById method
     *
     * @param id The id of the modality you are looking for
     * @return The modality found
     * @throws DataException
     */
    @Override
    public Modality findById(Long id) throws DataException {
        try {
            Optional<Modality> modality = modalityRepository.findById(id);
            if (modality.isPresent()) {
                return modality.get();
            } else {
                throw new NotFoundException("Modality not found");
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findAll method
     *
     * @return A list with all the modalities
     * @throws DataException
     */
    @Override
    public List<Modality> findAll() throws DataException {
        try {
            List<Modality> modalities = modalityRepository.findAll();
            if (modalities.isEmpty()) {
                throw new NotFoundException("Modalities not found");
            } else {
                return modalities;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the delete method
     *
     * @param id The id of the modality to be deleted
     * @throws DataException
     */
    @Override
    public void delete(Long id) throws DataException {
        try {
            modalityRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Modality not deleted", nre);
        }

    }

    /**
     * Implementation of the findByName method
     *
     * @param name The name of the modality you are looking for
     * @return The modality found
     * @throws DataException
     */
    @Override
    public Modality findByName(String name) throws DataException {
        try {
            Modality modality = modalityRepository.findByName(name);
            if (modality == null) {
                throw new NotFoundException("Modality not found");
            } else {
                return modality;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

    /**
     * Implementation of the findByNumberPlayers method
     *
     * @param numberPlayers The number players of the modality you are looking for
     * @return A list with found modalities
     * @throws DataException
     */
    @Override
    public List<Modality> findByNumberPlayers(int numberPlayers) throws DataException {
        try {
            List<Modality> modalities = modalityRepository.findByNumberPlayers(numberPlayers);
            if (modalities.isEmpty()) {
                throw new NotFoundException("Modalities not found");
            } else {
                return modalities;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }
}
