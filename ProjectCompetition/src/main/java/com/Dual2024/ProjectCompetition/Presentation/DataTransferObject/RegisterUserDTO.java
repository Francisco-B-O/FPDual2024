package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import com.Dual2024.ProjectCompetition.Utils.UserState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The Register user dto.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegisterUserDTO {
    @NotBlank
    private String nick;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    private String avatar;

    @Builder.Default
    private UserState state = UserState.CONECTADO;

}
