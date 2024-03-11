package com.Dual2024.ProjectCompetition.business.business_object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The Modality business object.
 *
 * @author Francisco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ModalityBO {

    private Long id;

    private int numberPlayers;

    private String name;

}
