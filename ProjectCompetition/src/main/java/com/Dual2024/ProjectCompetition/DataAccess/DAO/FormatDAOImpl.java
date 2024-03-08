package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.EntityNotFoundException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;
import com.Dual2024.ProjectCompetition.DataAccess.Repository.FormatRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * FormatDAO interface implementation
 *
 * @author Franciosco Balonero Olivera
 */
@Repository
public class FormatDAOImpl implements FormatDAO {
    @Autowired
    private FormatRepository formatRepository;

    @Override
    public Format save(Format format) throws DataException {
        try {
            return formatRepository.save(format);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Format not saved", nre);
        }

    }


    @Override
    public Format findById(Long id) throws DataException {
        try {
            Optional<Format> format = formatRepository.findById(id);
            if (format.isPresent()) {
                return format.get();
            } else {
                throw new EntityNotFoundException("Format not found");
            }

        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    @Override
    public List<Format> findAll() throws DataException {
        try {
            List<Format> formats = formatRepository.findAll();
            if (formats.isEmpty()) {
                throw new EntityNotFoundException("Formats not found");
            } else {
                return formats;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    @Override
    public void delete(Long id) throws DataException {
        try {
            formatRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Format not deleted", nre);
        }

    }

    @Override
    public Format findByName(String name) throws DataException {
        try {
            Format format = formatRepository.findByName(name);
            if (format == null) {
                throw new EntityNotFoundException("Format not found");
            } else {
                return format;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

}
