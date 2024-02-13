package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TeamBOAux {
	private Long id;

	private String name;

	private ModalityBO modality;

	private String logo;

}
