package com.dual2024.projectcompetition.dataaccess.dao;

import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.Format;

import java.util.List;

/**
 * Interface containing the FormatDAO methods.
 * This interface provides methods for interacting with format data in the data access layer.
 * Implementations of this interface handle the storage, retrieval, and deletion of format entities.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation to save a format
 *     Format savedFormat = formatDAO.save(newFormat);
 *
 *     // Data access operation to find a format by ID
 *     Format foundFormat = formatDAO.findById(formatId);
 *
 *     // Data access operation to retrieve a list of all formats
 *     List<Format> allFormats = formatDAO.findAll();
 *
 *     // Data access operation to delete a format by ID
 *     formatDAO.delete(formatId);
 *
 *     // Data access operation to find a format by name
 *     Format formatByName = formatDAO.findByName("formatName");
 * } catch (DataException e) {
 *     // Handle the exception
 *     logger.error("Data access error: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The interface defines methods for saving, finding by ID, finding all, deleting by ID, and finding by name.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface FormatDAO {
    /**
     * Saves a format.
     *
     * @param format Format to save.
     * @return Saved format.
     * @throws DataException If there is an issue saving the format.
     */
    Format save(Format format) throws DataException;

    /**
     * Finds a format by its ID.
     *
     * @param id The ID of the format to find.
     * @return The format found.
     * @throws DataException If there is an issue retrieving the format.
     */
    Format findById(Long id) throws DataException;

    /**
     * Retrieves a list of all formats.
     *
     * @return A list with all the formats.
     * @throws DataException If there is an issue retrieving the formats.
     */
    List<Format> findAll() throws DataException;

    /**
     * Deletes a format by its ID.
     *
     * @param id The ID of the format to be deleted.
     * @throws DataException If there is an issue deleting the format.
     */
    void delete(Long id) throws DataException;

    /**
     * Finds a format by its name.
     *
     * @param name The name of the format to find.
     * @return The format found.
     * @throws DataException If there is an issue retrieving the format by name.
     */
    Format findByName(String name) throws DataException;
}
