package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating the email already exists in a user.
 */
public class DuplicatedEmailException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 151017868746738093L;

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public DuplicatedEmailException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public DuplicatedEmailException(String message, Exception e) {
        super(message, e);
    }
}
