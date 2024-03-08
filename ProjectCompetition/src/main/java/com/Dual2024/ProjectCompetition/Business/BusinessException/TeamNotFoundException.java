package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a team is not found.
 */
public class TeamNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 7768296815177248971L;

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public TeamNotFoundException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public TeamNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
