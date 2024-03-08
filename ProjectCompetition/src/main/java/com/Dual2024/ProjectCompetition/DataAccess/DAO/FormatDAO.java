package com.Dual2024.ProjectCompetition.DataAccess.DAO;

import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.Format;

import java.util.List;

/**
 * Interface containing the FormatDAO methods
 *
 * @author Francisco Balonero Olivera
 */
public interface FormatDAO {
    /**
     * Method that saves a format
     *
     * @param format Format to save
     * @return Saved format
     * @throws DataException the data exception
     */
    Format save(Format format) throws DataException;

    /**
     * Method that searches a format by id
     *
     * @param id The id of the format you are looking for
     * @return The format found
     * @throws DataException the data exception
     */
    Format findById(Long id) throws DataException;

    /**
     * Method that returns a list with all the formats
     *
     * @return A list with all the formats
     * @throws DataException the data exception
     */
    List<Format> findAll() throws DataException;

    /**
     * Method that deletes a format by id
     *
     * @param id The id of the format to be deleted
     * @throws DataException the data exception
     */
    void delete(Long id) throws DataException;

    /**
     * Method that searches a format by name
     *
     * @param name The name of the format you are looking for
     * @return The format found
     * @throws DataException the data exception
     */
    Format findByName(String name) throws DataException;
}
