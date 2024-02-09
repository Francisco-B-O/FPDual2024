package com.Dual2024.ProjectCompetition.DataAccess.Model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "tournaments", uniqueConstraints = { @UniqueConstraint(name = "Tournament_name_modality", columnNames = {
		"tournament_name", "tournament_modality_id" }) })
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tournament_id")
	private Long id;

	@NotBlank
	@Column(name = "tournament_name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "tournament_size", nullable = false)
	private Integer size;

	@ManyToOne
	@JoinColumn(name = "tournament_format_id", nullable = false)
	private Format format;

	@NotNull
	@Column(name = "tournament_start_date", nullable = false)
	private LocalDateTime startDate;

	@NotNull
	@Column(name = "tournament_end_date", nullable = false)
	private LocalDateTime endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "tournament_state", nullable = false)
	private TournamentState state;

	@Lob
	@Column(name = "tournament_logo")
	private String logo;

	@NotBlank
	@Column(name = "tournament_description", nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "tournament_modality_id", nullable = false)
	private Modality modality;

	@ManyToMany(mappedBy = "tournaments")
	private List<Team> teams;
}