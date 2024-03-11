package com.dual2024.projectcompetition.dataaccess.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Entity class representing the "modalities" table in the database.
 * This class defines the structure and properties of modality entities.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "modalities")
public class Modality {
    /**
     * Entity fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modality_id")
    private Long id;

    @Column(name = "modality_number_players", nullable = false)
    private int numberPlayers;

    @NotBlank
    @Column(name = "modality_name", nullable = false, unique = true)
    private String name;
}
