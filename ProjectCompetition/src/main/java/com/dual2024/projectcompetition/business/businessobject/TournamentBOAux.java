package com.dual2024.projectcompetition.business.businessobject;

import com.dual2024.projectcompetition.utils.TournamentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Tournament aux business object.
 *
 * @author Francisco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TournamentBOAux {

    private Long id;

    private String name;

    private int size;

    private FormatBO format;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private TournamentState state;

    private String logo;

    private String description;

    private ModalityBO modality;
}
