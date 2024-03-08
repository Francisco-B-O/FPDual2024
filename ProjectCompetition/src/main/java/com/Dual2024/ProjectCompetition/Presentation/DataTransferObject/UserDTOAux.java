package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import java.util.List;

import com.Dual2024.ProjectCompetition.Utils.UserState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserDTOAux {
	private Long id;

	private String nick;

	private String email;

	private String avatar;

	private UserState state;

	private List<RoleDTO> roles;

}
