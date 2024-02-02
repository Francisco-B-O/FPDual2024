package com.Dual2024.ProjectCompetition.Model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Long id;

	@NotBlank
	@Column(name = "team_name", nullable = false, unique = true)
	private String name;

	@ManyToOne
	@JoinColumn(name = "team_modality_id", nullable = false)
	private Modality modality;

	@Lob
	@Column(name = "team_logo")
	private String logo;

	@ManyToMany(mappedBy = "teams")
	private List<User> users;

	@ManyToMany
	@JoinTable(name = "tournaments_teams", joinColumns = @JoinColumn(name = "tournaments_teams_tournament_id"), inverseJoinColumns = @JoinColumn(name = "tournaments_teams_team_id"))
	private List<Tournament> tournaments;
}
