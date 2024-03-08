package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Generic business layer exception
 *
 * @author Franciosco Balonero Olivera
 */
public class BusinessException extends Exception {

    @Serial
    private static final long serialVersionUID = -1618850050663333984L;

    /**
     * Builder with message
     *
     * @param message Message of the exception
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Builder with message and exception
     *
     * @param message Message of the exception
     * @param e       Cause
     */
    public BusinessException(String message, Exception e) {
        super(message, e);
    }
}
