package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that the date is invalid.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * due to an invalid date in the tournament.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw InvalidDateException
 *     businessService.validateTournamentDate();
 * } catch (InvalidDateException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error validating tournament date: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The exception includes a serial version UID for serialization purposes. It provides
 * two constructors, one with a message and another with both a message and a cause.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see BusinessException
 */
public class InvalidDateException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 3800928255694765328L;

    /**
     * Constructs a new InvalidDateException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidDateException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidDateException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception} The cause (which is saved for later retrieval by the getCause() method)
     */
    public InvalidDateException(String message, Exception cause) {
        super(message, cause);
    }
}
