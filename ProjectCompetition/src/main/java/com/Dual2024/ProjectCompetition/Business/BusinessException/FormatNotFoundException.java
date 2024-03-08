package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a format is not found.
 */
public class FormatNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 2681247367923842688L;

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public FormatNotFoundException(String message, Exception e) {
        super(message, e);

    }

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public FormatNotFoundException(String message) {
        super(message);

    }
}
