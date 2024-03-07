package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
	private String jwt;
}
