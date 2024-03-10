package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.FormatRepository;
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
            log.info("Format saved successfully with id: {}", savedFormat.getId());
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
            log.info("Format found by id: {}", id);
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
            log.info("Format deleted successfully with id: {}", id);
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
            log.info("Format found by name: {}", name);
            return format;
        } catch (NestedRuntimeException nre) {
            log.error("Error finding format by name: {}", name, nre);
            throw new DataException("Data access error", nre);
        }
    }

}
