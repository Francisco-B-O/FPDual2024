package com.Dual2024.ProjectCompetition.DataAccess.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "modalities")
public class Modality {

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
