package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception that indicates that a team is complete
 */
public class FullTeamException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 2338863615941165003L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public FullTeamException(String message) {
        super(message);
    }


    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public FullTeamException(String message, Exception e) {
        super(message, e);
    }
}
