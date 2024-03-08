package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a user is registered on a team with an active tournament.
 */
public class UserInActiveTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -1777617730921569922L;

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public UserInActiveTournamentException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public UserInActiveTournamentException(String message, Exception e) {
        super(message, e);
    }
}
