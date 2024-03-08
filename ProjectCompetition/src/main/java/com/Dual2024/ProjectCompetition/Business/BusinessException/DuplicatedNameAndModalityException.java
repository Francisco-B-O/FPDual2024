package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that the combination of name and modality is already used.
 */
public class DuplicatedNameAndModalityException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -4699899582060420276L;

    /**
     * Builder with message.
     *
     * @param message Message of the exception
     */
    public DuplicatedNameAndModalityException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public DuplicatedNameAndModalityException(String message, Exception e) {
        super(message, e);
    }
}
