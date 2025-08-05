package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.dataexception.EntityNotFoundException;
import com.dual2024.projectcompetition.dataaccess.model.Format;
import com.dual2024.projectcompetition.dataaccess.repository.FormatRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FormatDAO interface implementation
 *
 * @author Franciosco Balonero Olivera
 * @see FormatDAO
 */
@Slf4j
@Repository
public class FormatDAOImpl implements FormatDAO {
    @Autowired
    private FormatRepository formatRepository;

    @Override
    public Format save(Format format) throws DataException {
        try {
            Format savedFormat = formatRepository.save(format);
            log.debug("Format saved successfully with id: {}", savedFormat.getId());
            log.info("Format saved successfully");
            return savedFormat;
        } catch (NestedRuntimeException | ConstraintViolationException e) {
            log.error("Error saving format", e);
            throw new DataException("Format not saved", e);
        }
    }

    @Override
    public Format findById(Long id) throws DataException {
        try {
            Format format = formatRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Format not found"));
            log.debug("Format found by id: {}", id);
            log.info("Format found by id");
            return format;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding format by id: {}", id, nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public List<Format> findAll() throws DataException {
        try {
            List<Format> formats = formatRepository.findAll();
            if (formats.isEmpty()) {
                log.warn("No formats found");
                throw new EntityNotFoundException("Formats not found");
            } else {
                log.info("Found {} formats", formats.size());
                return formats;
            }
        } catch (NestedRuntimeException nre) {
            log.error("Error finding all formats", nre);
            throw new DataException("Data access error", nre);
        }
    }

    @Override
    public void delete(Long id) throws DataException {
        try {
            formatRepository.deleteById(id);
            log.debug("Format deleted successfully with id: {}", id);
            log.info("Format deleted successfully");
        } catch (NestedRuntimeException nre) {
            log.error("Error deleting format with id: {}", id, nre);
            throw new DataException("Format not deleted", nre);
        }
    }

    @Override
    public Format findByName(String name) throws DataException {
        try {
            Format format = formatRepository.findByName(name)
                    .orElseThrow(() -> new EntityNotFoundException("Format not found"));
            log.debug("Format found by name: {}", name);
            log.info("Format found by name");
            return format;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding format by name: {}", name, nre);
            throw new DataException("Data access error", nre);
        }
    }

}
