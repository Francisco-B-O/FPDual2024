package com.Dual2024.ProjectCompetition.DataAccess.DataException;

import java.io.Serial;

/**
 * Generic data access layer exception
 *
 * @author Franciosco Balonero Olivera
 */
public class DataException extends Exception {

    @Serial
    private static final long serialVersionUID = -5547352001279328620L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public DataException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public DataException(String message, Exception e) {
        super(message, e);
    }
}
