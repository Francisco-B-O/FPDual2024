package com.Dual2024.ProjectCompetition.Presentation.Exception;

import java.io.Serial;

/**
 * Exception indicating that something has not been found
 *
 * @author Franciosco Balonero Olivera
 */
public class NotFoundException extends PresentationException {

    @Serial
    private static final long serialVersionUID = -7198817472286130251L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public NotFoundException(String message, Exception e) {
        super(message, e);
    }
}
