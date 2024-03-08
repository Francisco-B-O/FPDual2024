package com.Dual2024.ProjectCompetition.DataAccess.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Roles table entity class
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
