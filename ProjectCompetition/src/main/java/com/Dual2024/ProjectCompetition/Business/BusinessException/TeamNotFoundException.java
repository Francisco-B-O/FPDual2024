package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a team is not found.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * * because the specified team was not found.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw TeamNotFoundException
 *     businessService.performOperation();
 * } catch (TeamNotFoundException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error performing the operation: " + e.getMessage(), e);
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
public class TeamNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 7768296815177248971L;

    /**
     * Constructs a new TeamNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public TeamNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new TeamNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public TeamNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
