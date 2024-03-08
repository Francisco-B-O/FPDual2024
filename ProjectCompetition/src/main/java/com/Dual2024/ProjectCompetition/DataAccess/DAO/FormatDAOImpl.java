package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.NotFoundException;
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

    /**
     * Implementation of the save method
     *
     * @param format Format to save
     * @return Saved format
     * @throws DataException
     */
    @Override
    public Format save(Format format) throws DataException {
        try {
            return formatRepository.save(format);
        } catch (NestedRuntimeException | ConstraintViolationException nre) {
            throw new DataException("Format not saved", nre);
        }

    }

    /**
     * Implementation of the findById method
     *
     * @param id The id of the format you are looking for
     * @return The format found
     * @throws DataException
     */
    @Override
    public Format findById(Long id) throws DataException {
        try {
            Optional<Format> format = formatRepository.findById(id);
            if (format.isPresent()) {
                return format.get();
            } else {
                throw new NotFoundException("Format not found");
            }

        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the findAll method
     *
     * @return A list with all the formats
     * @throws DataException
     */
    @Override
    public List<Format> findAll() throws DataException {
        try {
            List<Format> formats = formatRepository.findAll();
            if (formats.isEmpty()) {
                throw new NotFoundException("Formats not found");
            } else {
                return formats;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }

    }

    /**
     * Implementation of the delete method
     *
     * @param id The id of the format to be deleted
     * @throws DataException
     */
    @Override
    public void delete(Long id) throws DataException {
        try {
            formatRepository.deleteById(id);
        } catch (NestedRuntimeException nre) {
            throw new DataException("Format not deleted", nre);
        }

    }

    /**
     * Implementation of the findByName method
     *
     * @param name The name of the format you are looking for
     * @return The format found
     * @throws DataException
     */
    @Override
    public Format findByName(String name) throws DataException {
        try {
            Format format = formatRepository.findByName(name);
            if (format == null) {
                throw new NotFoundException("Format not found");
            } else {
                return format;
            }
        } catch (NestedRuntimeException nre) {
            throw new DataException("Data access error", nre);
        }
    }

}
