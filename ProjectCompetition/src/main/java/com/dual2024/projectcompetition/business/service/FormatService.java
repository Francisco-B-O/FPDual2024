package com.dual2024.projectcompetition.business.service;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessobject.FormatBO;

import java.util.List;

/**
 * Service interface containing methods for managing formats.
 *
 * <p>This interface declares methods for performing CRUD operations on formats. It includes methods
 * to add a new format, retrieve a format by ID, retrieve all formats, delete a format, retrieve a
 * format by name, and update a format.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Obtain an instance of FormatService
 * FormatService formatService = // instantiate or inject the implementation
 *
 * // Add a new format
 * FormatBO newFormat = FormatBO.builder().name("New Format").build();
 * FormatBO addedFormat = formatService.addFormat(newFormat);
 *
 * // Retrieve a format by ID
 * Long formatId = 1L;
 * FormatBO retrievedFormat = formatService.getFormatById(formatId);
 *
 * // Retrieve all formats
 * List<FormatBO> allFormats = formatService.getAllFormats();
 *
 * // Delete a format by ID
 * Long formatToDeleteId = 2L;
 * formatService.deleteFormat(formatToDeleteId);
 *
 * // Retrieve a format by name
 * String formatName = "Sample Format";
 * FormatBO formatByName = formatService.getFormatByName(formatName);
 *
 * // Update a format
 * FormatBO formatToUpdate = // retrieve a format or create a new one
 * formatToUpdate.setName("Updated Format");
 * FormatBO updatedFormat = formatService.updateFormat(formatToUpdate);
 * }
 * </pre>
 *
 * <p>Implementing classes should handle {@link BusinessException} to appropriately manage exceptions
 * related to business operations on formats.</p>
 *
 * <p>Instances of this interface are typically used in the business layer of the application to provide
 * a clean separation between the business logic and data access logic.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface FormatService {
    /**
     * Adds a new format.
     *
     * @param formatBO The format business object
     * @return The saved format business object
     * @throws BusinessException If an error occurs during the operation
     */
    FormatBO addFormat(FormatBO formatBO) throws BusinessException;

    /**
     * Retrieves a format by ID.
     *
     * @param id The ID of the format
     * @return The format business object
     * @throws BusinessException If the format is not found or an error occurs during the operation
     */
    FormatBO getFormatById(Long id) throws BusinessException;

    /**
     * Retrieves all formats.
     *
     * @return A list of all formats
     * @throws BusinessException If an error occurs during the operation
     */
    List<FormatBO> getAllFormats() throws BusinessException;

    /**
     * Deletes a format by ID.
     *
     * @param id The ID of the format to be deleted
     * @throws BusinessException If the format is not found or an error occurs during the operation
     */
    void deleteFormat(Long id) throws BusinessException;

    /**
     * Retrieves a format by name.
     *
     * @param name The name of the format
     * @return The format business object
     * @throws BusinessException If the format is not found or an error occurs during the operation
     */
    FormatBO getFormatByName(String name) throws BusinessException;

    /**
     * Updates a format.
     *
     * @param formatBO The updated format business object
     * @return The updated format business object
     * @throws BusinessException If the format is not found or an error occurs during the operation
     */
    FormatBO updateFormat(FormatBO formatBO) throws BusinessException;
}
