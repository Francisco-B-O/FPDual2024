package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a user is not found
 */
public class UserNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -5004348401632046351L;

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public UserNotFoundException(String message, Exception e) {
        super(message, e);

    }

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public UserNotFoundException(String message) {
        super(message);

    }
}
