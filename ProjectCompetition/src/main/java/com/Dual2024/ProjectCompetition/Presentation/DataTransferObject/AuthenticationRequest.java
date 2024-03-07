package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
	private String email;
	private String password;
}
