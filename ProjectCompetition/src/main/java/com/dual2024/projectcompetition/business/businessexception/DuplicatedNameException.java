package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that the name is already in use.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because there is an attempt to register a name that is already use.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw DuplicatedNameException
 *     businessService.createTournament();
 * } catch (DuplicatedNameException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error creating tournament: " + e.getMessage(), e);
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
public class DuplicatedNameException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -2232920202794886547L;

    /**
     * Constructs a new DuplicatedNameException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DuplicatedNameException(String message) {
        super(message);
    }

    /**
     * Constructs a new DuplicatedNameException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception}    The cause (which is saved for later retrieval by the getCause() method)
     */
    public DuplicatedNameException(String message, Exception cause) {
        super(message, cause);
    }
}
