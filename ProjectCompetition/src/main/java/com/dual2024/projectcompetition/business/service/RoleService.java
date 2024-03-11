package com.dual2024.projectcompetition.business.service;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessobject.RoleBO;

import java.util.List;

/**
 * Service interface containing methods for managing roles.
 *
 * <p>This interface declares a method to retrieve all roles in the system.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Obtain an instance of RoleService
 * RoleService roleService = // instantiate or inject the implementation
 *
 * // Retrieve all roles
 * List<RoleBO> allRoles = roleService.getAllRoles();
 * }
 * </pre>
 *
 * <p>Implementing classes should handle {@link BusinessException} to appropriately manage exceptions
 * related to business operations on roles.</p>
 *
 * <p>Instances of this interface are typically used in the business layer of the application to provide
 * a clean separation between the business logic and data access logic.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface RoleService {
    /**
     * Retrieves all roles.
     *
     * @return A list of all roles
     * @throws BusinessException If an error occurs during the operation
     */
    List<RoleBO> getAllRoles() throws BusinessException;
}
