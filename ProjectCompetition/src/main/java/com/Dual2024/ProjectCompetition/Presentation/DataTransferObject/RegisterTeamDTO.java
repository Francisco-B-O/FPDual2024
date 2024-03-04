package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

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
public class RegisterTeamDTO {
	@NotBlank
	private String name;
	@NotNull
	private ModalityDTO modality;

	private String logo;

}
