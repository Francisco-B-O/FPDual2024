package com.dual2024.projectcompetition.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The Team dto aux.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TeamDTOAux {
    private Long id;

    private String name;

    private ModalityDTO modality;

    private String logo;

    private Long captainId;
}
