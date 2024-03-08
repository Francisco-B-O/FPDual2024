package com.Dual2024.ProjectCompetition.Business.BusinessObject;

import com.Dual2024.ProjectCompetition.Utils.TournamentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The Team business object
 *
 * @author Francisco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TeamBO {

    private Long id;

    private String name;

    private ModalityBO modality;

    private String logo;

    private Long captainId;

    private List<UserBOAux> users;

    private List<TournamentBOAux> tournaments;

    /**
     * Is in active tournament boolean.
     *
     * @return the boolean
     */
    public boolean isInActiveTournament() {
        boolean isInActiveTournament = false;
        for (TournamentBOAux tournament : this.tournaments) {
            if (tournament.getState().equals(TournamentState.EN_JUEGO)) {
                isInActiveTournament = true;
            }
        }
        return isInActiveTournament;
    }

}
