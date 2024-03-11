package com.Dual2024.ProjectCompetition.DataAccess.data_exception;

import java.io.Serial;

/**
 * Custom exception class for generic data access layer exceptions.
 *
 * <p>This exception is intended to be thrown when an error occurs during data access operations.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation
 *     // ...
 * } catch (DataException e) {
 *     // Handle the exception
 *     logger.error("Data access error: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The exception includes both a message describing the error and, if available, the original cause of the exception.</p>
 *
 * @author Franciosco Balonero Olivera
 */
public class DataException extends Exception {

    @Serial
    private static final long serialVersionUID = -5547352001279328620L;

    /**
     * Constructs a new DataException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DataException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public DataException(String message, Exception cause) {
        super(message, cause);
    }
}
