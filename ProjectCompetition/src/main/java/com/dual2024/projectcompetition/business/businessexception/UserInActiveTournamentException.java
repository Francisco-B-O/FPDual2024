package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that a user is registered on a team with an active tournament.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because the user is already registered on a team that is participating in an active tournament.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw UserInActiveTournamentException
 *     businessService.performOperation();
 * } catch (UserInActiveTournamentException e) {
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
public class UserInActiveTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -1777617730921569922L;

    /**
     * Constructs a new UserInActiveTournamentException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public UserInActiveTournamentException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserInActiveTournamentException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception}  The cause (which is saved for later retrieval by the getCause() method)
     */
    public UserInActiveTournamentException(String message, Exception cause) {
        super(message, cause);
    }
}
