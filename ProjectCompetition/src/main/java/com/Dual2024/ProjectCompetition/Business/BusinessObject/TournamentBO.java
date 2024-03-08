package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Tournament business object
 *
 * @author Francisco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TournamentBO {

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

    private List<TeamBO> teams;
}
