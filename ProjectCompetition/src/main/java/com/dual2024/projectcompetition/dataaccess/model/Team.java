package com.dual2024.projectcompetition.dataaccess.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Entity class representing the "teams" table in the database.
 * This class defines the structure and properties of team entities.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "teams", uniqueConstraints = {
        @UniqueConstraint(name = "Team_name_modality", columnNames = {"team_name", "team_modality_id"})})
public class Team {
    /**
     * Entity fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "team_name", nullable = false)
    private String name;

    @ManyToOne
    @NotNull(message = "Modality cannot be null")
    @JoinColumn(name = "team_modality_id", nullable = false)
    private Modality modality;

    @ManyToOne
    @JoinColumn(name = "team_captain_id", nullable = false)
    private User captain;

    @Lob
    @Column(name = "team_logo")
    private String logo;

    @ManyToMany
    @JoinTable(name = "users_teams", joinColumns = @JoinColumn(name = "users_teams_user_id"), inverseJoinColumns = @JoinColumn(name = "users_teams_team_id"))
    private List<User> users;

    @ManyToMany(mappedBy = "teams")
    private List<Tournament> tournaments;
}
