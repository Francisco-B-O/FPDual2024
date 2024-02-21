package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import java.time.LocalDateTime;
import java.util.List;

import com.Dual2024.ProjectCompetition.DataAccess.Model.TournamentState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TournamentBO {

	private Long id;

	private String name;

	private int size;

	private FormatBO format;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private TournamentState state;

	private String logo;

	private String description;

	private ModalityBO modality;

	private List<TeamBOAux> teams;
}
