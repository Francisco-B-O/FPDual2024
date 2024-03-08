package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception that indicates that a tournament is complete.
 */
public class FullTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -2375722109173938280L;

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public FullTournamentException(String message) {
        super(message);
    }


    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public FullTournamentException(String message, Exception e) {
        super(message, e);
    }
}
