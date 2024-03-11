package com.Dual2024.ProjectCompetition.business.business_exception;

import java.io.Serial;

/**
 * Exception indicating that a team is complete.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because the specified team is already complete and cannot accept additional players.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw FullTeamException
 *     businessService.joinTeam();
 * } catch (FullTeamException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error joining team: " + e.getMessage(), e);
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
public class FullTeamException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 2338863615941165003L;

    /**
     * Constructs a new FullTeamException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public FullTeamException(String message) {
        super(message);
    }

    /**
     * Constructs a new FullTeamException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public FullTeamException(String message, Exception cause) {
        super(message, cause);
    }
}
