package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import java.time.LocalDateTime;

import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TournamentDTOAux {
	private Long id;

	private String name;

	private int size;

	private FormatDTO format;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private TournamentState state;

	private String logo;

	private String description;

	private ModalityDTO modality;
}
