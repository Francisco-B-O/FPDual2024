package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The Role business object
 *
 * @author Francisco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RoleBO {

    private Long id;

    private String name;

    private String description;

}
