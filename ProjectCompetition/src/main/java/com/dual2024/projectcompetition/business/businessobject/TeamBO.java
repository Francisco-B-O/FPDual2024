package com.dual2024.projectcompetition.business.businessobject;

import com.dual2024.projectcompetition.utils.TournamentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The Team business object.
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
     * Method that returns {@code false} if the team is not in an active tournament or {@code true} if they are.
     *
     * @return The boolean
     */
    public boolean isInActiveTournament() {
        return this.tournaments.stream().anyMatch(c -> c.getState().equals(TournamentState.EN_JUEGO));
    }

}
