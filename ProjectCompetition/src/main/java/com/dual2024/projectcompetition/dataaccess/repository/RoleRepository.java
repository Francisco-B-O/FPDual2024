package com.dual2024.projectcompetition.dataaccess.repository;

import com.dual2024.projectcompetition.dataaccess.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for performing CRUD operations on Role.
 *
 * <p>This interface extends the JpaRepository interface, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on Role entities. The methods declared in this interface are automatically implemented by Spring Data JPA.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // Data access operation to find the role with the specified name
 * Optional<Role> role = roleRepository.findByName("JUGADOR");
 * }
 * </pre>
 *
 * <p>The interface includes a method to find the role by name.</p>
 *
 * @author Francisco Balonero Olivera
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Finds the role with the specified name.
     *
     * @param name the name of the role
     * @return the role with the specified name
     */
    Optional<Role> findByName(String name);
}
