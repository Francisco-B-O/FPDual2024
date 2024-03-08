package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating the nick already exists in a user.
 */
public class DuplicatedNickException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 5235794232694134448L;

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public DuplicatedNickException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public DuplicatedNickException(String message, Exception e) {
        super(message, e);
    }
}
