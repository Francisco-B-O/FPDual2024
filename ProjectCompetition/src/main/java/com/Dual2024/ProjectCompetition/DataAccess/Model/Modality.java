package com.Dual2024.ProjectCompetition.DataAccess.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Modalities table entity class
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
