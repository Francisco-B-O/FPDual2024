package com.dual2024.projectcompetition.business.businessobject;

import com.dual2024.projectcompetition.utils.TournamentState;
import com.dual2024.projectcompetition.utils.UserState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The User business object.
 *
 * @author Francisco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserBO {

    private Long id;

    private String nick;

    private String email;

    private String password;

    private String avatar;

    private UserState state;

    private List<RoleBO> roles;

    private List<TeamBO> teams;

    /**
     * Method that returns {@code false} if the user is not in an active tournament or {@code true} if they are
     *
     * @return The boolean
     */
    public boolean isInActiveTournament() {
        return this.teams.stream().iterator().next().getTournaments().stream()
                .anyMatch(c -> c.getState().equals(TournamentState.EN_JUEGO));
    }

}
