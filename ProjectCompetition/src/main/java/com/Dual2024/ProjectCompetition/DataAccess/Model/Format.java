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
@Table(name = "formats")
public class Format {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "format_id")
	private Long id;

	@NotBlank
	@Column(name = "format_name", nullable = false, unique = true)
	private String name;
}