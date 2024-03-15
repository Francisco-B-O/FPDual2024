package com.dual2024.projectcompetition.dataaccess.model;

import com.dual2024.projectcompetition.utils.TournamentState;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity class representing the "tournaments" table in the database.
 * This class defines the structure and properties of tournament entities.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "tournaments", uniqueConstraints = {@UniqueConstraint(name = "Tournament_name_modality", columnNames = {
        "tournament_name", "tournament_modality_id"})})
public class Tournament {
    /**
     * Entity fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "tournament_name", nullable = false)
    private String name;

    @Min(value = 2, message = "Size must be at least 2")
    @Column(name = "tournament_size", nullable = false)
    private int size;

    @NotNull(message = "Format cannot be null")
    @ManyToOne
    @JoinColumn(name = "tournament_format_id", nullable = false)
    private Format format;

    @FutureOrPresent(message = "Start date must be in the present or future")
    @Column(name = "tournament_start_date", nullable = false)
    private LocalDateTime startDate;

    @Future(message = "End date must be in the future")
    @Column(name = "tournament_end_date", nullable = false)
    private LocalDateTime endDate;

    @NotNull(message = "State cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "tournament_state", nullable = false)
    private TournamentState state;

    @Lob
    @Column(name = "tournament_logo")
    private String logo;

    @NotBlank(message = "Description cannot be blank")
    @Column(name = "tournament_description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "tournament_modality_id", nullable = false)
    private Modality modality;

    @NotNull(message = "Modality cannot be null")
    @ManyToMany
    @JoinTable(name = "tournaments_teams", joinColumns = @JoinColumn(name = "tournaments_teams_tournament_id"), inverseJoinColumns = @JoinColumn(name = "tournaments_teams_team_id"))
    private List<Team> teams;
}
