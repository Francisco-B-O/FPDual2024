package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that a tournament is complete.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because the specified tournament is already complete and cannot accept additional teams.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw FullTournamentException
 *     businessService.registerForTournament();
 * } catch (FullTournamentException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error registering for the tournament: " + e.getMessage(), e);
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
public class FullTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -2375722109173938280L;

    /**
     * Constructs a new FullTournamentException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public FullTournamentException(String message) {
        super(message);
    }

    /**
     * Constructs a new FullTournamentException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception}    The cause (which is saved for later retrieval by the getCause() method)
     */
    public FullTournamentException(String message, Exception cause) {
        super(message, cause);
    }
}
