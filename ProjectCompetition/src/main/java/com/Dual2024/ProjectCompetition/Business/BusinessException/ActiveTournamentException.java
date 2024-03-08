package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a tournament is active
 *
 * @author Franciosco Balonero Olivera
 */
public class ActiveTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -448578762742195642L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public ActiveTournamentException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public ActiveTournamentException(String message, Exception e) {
        super(message, e);
    }
}
