package com.dual2024.projectcompetition.dataaccess.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Entity class representing the "formats" table in the database.
 * This class defines the structure and properties of format entities.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "formats")
public class Format {
    /**
     * Entity fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "format_id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "format_name", nullable = false, unique = true)
    private String name;
}
