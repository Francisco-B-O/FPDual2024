package com.Dual2024.ProjectCompetition.business.business_exception;

import java.io.Serial;

/**
 * Exception indicating that a tournament is active.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because there is an active tournament that conflicts with the operation's requirements.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw ActiveTournamentException
 *     businessService.performOperation();
 * } catch (ActiveTournamentException e) {
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
public class ActiveTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -448578762742195642L;

    /**
     * Constructs a new ActiveTournamentException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ActiveTournamentException(String message) {
        super(message);
    }

    /**
     * Constructs a new ActiveTournamentException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public ActiveTournamentException(String message, Exception cause) {
        super(message, cause);
    }
}
