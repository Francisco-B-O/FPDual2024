package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a team is registered for an active tournament.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because a team is registered for an active tournament, and the operation conflicts
 * with the active tournament's requirements.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw TeamInActiveTournamentException
 *     businessService.performOperation();
 * } catch (TeamInActiveTournamentException e) {
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
public class TeamInActiveTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 4447640950544648399L;

    /**
     * Constructs a new TeamInActiveTournamentException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public TeamInActiveTournamentException(String message) {
        super(message);
    }

    /**
     * Constructs a new TeamInActiveTournamentException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public TeamInActiveTournamentException(String message, Exception cause) {
        super(message, cause);
    }
}
