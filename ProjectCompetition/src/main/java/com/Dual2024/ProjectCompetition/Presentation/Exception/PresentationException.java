package com.Dual2024.ProjectCompetition.Presentation.Exception;

import java.io.Serial;

/**
 * Generic presentation layer exception
 *
 * @author Franciosco Balonero Olivera
 */
public class PresentationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1641575469051626634L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public PresentationException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public PresentationException(String message, Exception e) {
        super(message, e);
    }
}
