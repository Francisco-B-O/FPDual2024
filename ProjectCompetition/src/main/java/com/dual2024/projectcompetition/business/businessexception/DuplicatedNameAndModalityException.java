package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that the combination of name and modality is already used.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because there is an attempt to register a combination of name and modality that already use.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw DuplicatedNameAndModalityException
 *     businessService.createTournament();
 * } catch (DuplicatedNameAndModalityException e) {
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
public class DuplicatedNameAndModalityException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -4699899582060420276L;

    /**
     * Constructs a new DuplicatedNameAndModalityException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DuplicatedNameAndModalityException(String message) {
        super(message);
    }

    /**
     * Constructs a new DuplicatedNameAndModalityException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public DuplicatedNameAndModalityException(String message, Exception cause) {
        super(message, cause);
    }
}
