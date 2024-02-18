package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserBOAux {

	private Long id;

	private String nick;

	private String email;

	private String password;

	private String avatar;

	private UserStateBO state;

	private List<RoleBO> roles;

}
