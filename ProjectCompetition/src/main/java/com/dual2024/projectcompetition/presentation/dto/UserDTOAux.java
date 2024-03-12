package com.dual2024.projectcompetition.presentation.dto;

import com.dual2024.projectcompetition.utils.UserState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Auxiliary DTO representing user information.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "Auxiliary DTO representing user information")

public class UserDTOAux {
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "User's nickname", example = "Player01")
    private String nick;

    @Schema(description = "User's email address", example = "player01@email.com")
    private String email;

    @Schema(description = "Path to the user's avatar", example = "avatar.png")
    private String avatar;

    @Schema(description = "Current state of the user", example = "CONNECTED")
    private UserState state;

    @Schema(description = "Roles assigned to the user", example = "[ADMIN, MANAGER]")
    private List<RoleDTO> roles;
}
