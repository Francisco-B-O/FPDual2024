package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import java.util.List;

import com.Dual2024.ProjectCompetition.DataAccess.Model.Modality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TeamBO {

	private Long id;

	private String name;

	private Modality modality;

	private String logo;

	private List<UserBO> users;

	private List<TournamentBO> tournaments;
}
