package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The Format business object
 *
 * @author Francisco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder

public class FormatBO {

    private Long id;

    private String name;

}
