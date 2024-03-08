package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception that indicates that a team is registered for an active tournament
 */
public class TeamInActiveTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 4447640950544648399L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public TeamInActiveTournamentException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public TeamInActiveTournamentException(String message, Exception e) {
        super(message, e);
    }
}
