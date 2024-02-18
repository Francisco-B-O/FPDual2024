package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TournamentBOAux {

	private Long id;

	private String name;

	private int size;

	private FormatBO format;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private TournamentStateBO state;

	private String logo;

	private String description;

	private ModalityBO modality;
}
