package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import com.Dual2024.ProjectCompetition.Utils.UserState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The User dto.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    private String nick;

    private String email;

    private String password;

    private String avatar;

    private UserState state;

    private List<RoleDTO> roles;

    private List<TeamDTOAux> teams;
}
