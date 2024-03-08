package com.Dual2024.ProjectCompetition.DataAccess.DataException;

import java.io.Serial;

/**
 * Exception that indicates that what was searched for has not been found
 *
 * @author Franciosco Balonero Olivera
 */
public class EntityNotFoundException extends DataException {

    @Serial
    private static final long serialVersionUID = 8280183600637864595L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public EntityNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
