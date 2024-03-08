package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a tournament is not found.
 */
public class TournamentNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 271957121025982489L;

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public TournamentNotFoundException(String message, Exception e) {
        super(message, e);

    }

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public TournamentNotFoundException(String message) {
        super(message);

    }
}
