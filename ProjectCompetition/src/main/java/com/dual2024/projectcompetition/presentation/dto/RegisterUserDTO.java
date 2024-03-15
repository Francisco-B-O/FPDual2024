package com.dual2024.projectcompetition.presentation.dto;

import com.dual2024.projectcompetition.utils.UserState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO for registering a new user.
 *
 * @author : Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "DTO for registering a new user")
public class RegisterUserDTO {

    @Schema(description = "User's nickname", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Nickname cannot be blank")
    private String nick;

    @Schema(description = "User's email address", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Schema(description = "User's password", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Schema(description = "User's avatar", nullable = true)
    private String avatar;

    @Schema(description = "Initial state of the user", defaultValue = "CONNECTED")
    @Builder.Default
    private UserState state = UserState.CONNECTED;
}
