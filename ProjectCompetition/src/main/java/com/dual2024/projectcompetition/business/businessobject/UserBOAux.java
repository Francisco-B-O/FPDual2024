package com.Dual2024.ProjectCompetition.business.business_object;

import com.Dual2024.ProjectCompetition.Utils.UserState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The User aux business object.
 *
 * @author Francisco Balonero Olivera
 */
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

    private UserState state;

    private List<RoleBO> roles;

}
