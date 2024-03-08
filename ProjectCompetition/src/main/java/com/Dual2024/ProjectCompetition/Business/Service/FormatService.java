package com.Dual2024.ProjectCompetition.Business.Service;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.FormatBO;

import java.util.List;

/**
 * Interface that contains the methods of the format service.
 *
 * @author Francisco Balonero Olivera
 */
public interface FormatService {
    /**
     * Add format.
     *
     * @param formatBO The format bo
     * @return The saved format bo
     * @throws BusinessException
     */
    FormatBO addFormat(FormatBO formatBO) throws BusinessException;

    /**
     * Gets format by id.
     *
     * @param id The id
     * @return The format by id
     * @throws BusinessException
     */
    FormatBO getFormatById(Long id) throws BusinessException;

    /**
     * Gets all formats.
     *
     * @return All formats
     * @throws BusinessException
     */
    List<FormatBO> getAllFormats() throws BusinessException;

    /**
     * Delete format.
     *
     * @param id The id
     * @throws BusinessException
     */
    void deleteFormat(Long id) throws BusinessException;

    /**
     * Gets format by name.
     *
     * @param name The name
     * @return The format by name
     * @throws BusinessException
     */
    FormatBO getFormatByName(String name) throws BusinessException;

    /**
     * Update format.
     *
     * @param formatBO The format bo
     * @return The updated format bo
     * @throws BusinessException
     */
    FormatBO updateFormat(FormatBO formatBO) throws BusinessException;
}
