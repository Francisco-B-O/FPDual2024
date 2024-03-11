package com.dual2024.projectcompetition.dataaccess.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Entity class representing the "roles" table in the database.
 * This class defines the structure and properties of role entities.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {
    /**
     * Entity fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @NotBlank
    @Column(name = "role_name", nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(name = "role_description", nullable = false)
    private String description;
}
