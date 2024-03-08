package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that that name is already in use
 */
public class DuplicatedNameException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -2232920202794886547L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public DuplicatedNameException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public DuplicatedNameException(String message, Exception e) {
        super(message, e);
    }
}
