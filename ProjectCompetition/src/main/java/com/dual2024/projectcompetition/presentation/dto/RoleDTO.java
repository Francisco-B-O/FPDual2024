package com.Dual2024.ProjectCompetition.Presentation.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The Role dto.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RoleDTO {
    private Long id;

    private String name;

    private String description;
}
