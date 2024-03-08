package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a modality is not found,
 */
public class ModalityNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -115495127483418668L;

    /**
     * Builder with message and exception.
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public ModalityNotFoundException(String message, Exception e) {
        super(message, e);

    }


    /**
     * Builder with message
     *
     * @param message Message of the exception.
     */
    public ModalityNotFoundException(String message) {
        super(message);

    }
}
