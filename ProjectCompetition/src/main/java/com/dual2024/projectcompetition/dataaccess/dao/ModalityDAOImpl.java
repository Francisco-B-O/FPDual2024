package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Modality;
import com.dual2024.projectcompetition.dataaccess.repository.ModalityRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ModalityDAO interface implementation
 *
 * @author Francisco Balonero Olivera
 * @see ModalityDAO
 */
@Slf4j
@Repository
public class ModalityDAOImpl implements ModalityDAO {
    @Autowired
    private ModalityRepository modalityRepository;

    @Override
    public Modality save(Modality modality) throws DataException {
        try {
            Modality savedModality = modalityRepository.save(modality);
            log.info("Modality saved successfully with id: {}", savedModality.getId());
            return savedModality;
        } catch (NestedRuntimeException | ConstraintViolationException e) {
            log.error("Error saving modality", e);
            throw new DataException("Modality not saved", e);
        }
    }

    @Override
    public Modality findById(Long id) throws DataException {
        try {
            Modality modality = modalityRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Modality not found"));
            log.info("Modality found by id: {}", id);
            return modality;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding modality by id: {}", id, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Modality> findAll() throws DataException {
        try {
            List<Modality> modalities = modalityRepository.findAll();
            if (modalities.isEmpty()) {
                log.warn("No modalities found");
                throw new EntityNotFoundException("Modalities not found");
            } else {
                log.info("Found {} modalities", modalities.size());
                return modalities;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding all modalities", nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public void delete(Long id) throws DataException {
        try {
            modalityRepository.deleteById(id);
            log.info("Modality deleted successfully with id: {}", id);
        } catch (NestedRuntimeException nre) {
            log.error("Error deleting modality with id: {}", id, nre);
            throw new DataException("Modality not deleted", nre);
        }
    }

    @Override
    public Modality findByName(String name) throws DataException {
        try {
            Modality modality = modalityRepository.findByName(name)
                    .orElseThrow(() -> new EntityNotFoundException("Modality not found"));
            log.info("Modality found by name: {}", name);
            return modality;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding modality by name: {}", name, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Modality> findByNumberPlayers(int numberPlayers) throws DataException {
        try {
            List<Modality> modalities = modalityRepository.findByNumberPlayers(numberPlayers);
            if (modalities.isEmpty()) {
                log.warn("No modalities found with {} players", numberPlayers);
                throw new EntityNotFoundException("Modalities not found");
            } else {
                log.info("Found {} modalities with {} players", modalities.size(), numberPlayers);
                return modalities;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding modalities by number of players: {}", numberPlayers, nre);
            throw new DataException("Data access error", nre);
        }
    }
}
