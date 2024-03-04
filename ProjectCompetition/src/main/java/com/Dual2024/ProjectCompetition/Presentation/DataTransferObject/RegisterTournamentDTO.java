package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import java.time.LocalDateTime;

import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
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
public class RegisterTournamentDTO {
	@NotBlank
	private String name;

	@Min(2)
	private int size;

	@NotNull
	private FormatDTO format;

	@FutureOrPresent
	private LocalDateTime startDate;

	@Future
	private LocalDateTime endDate;

	@NotNull
	private TournamentState state;

	private String logo;

	@NotBlank
	private String description;

	@NotNull
	private ModalityDTO modality;
}
