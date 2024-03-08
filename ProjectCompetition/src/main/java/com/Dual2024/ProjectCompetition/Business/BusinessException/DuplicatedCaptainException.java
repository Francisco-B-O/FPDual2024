package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that the captain is duplicated
 */
public class DuplicatedCaptainException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 6009648072538957865L;


    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public DuplicatedCaptainException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public DuplicatedCaptainException(String message, Exception e) {
        super(message, e);
    }
}
