package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ModalityBO {

	private Long id;

	private int numberPlayers;

	private String name;

}
