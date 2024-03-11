package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The Update user dto.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UpdateUserDTO {

    @NotBlank
    @Size(min = 6)
    private String password;

    private String avatar;

    private List<RoleDTO> roles;
}
