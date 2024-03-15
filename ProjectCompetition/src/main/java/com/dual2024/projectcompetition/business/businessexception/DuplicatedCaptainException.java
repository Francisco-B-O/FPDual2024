package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that the captain is duplicated.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because there is an attempt to assign a duplicated captain to a team.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw DuplicatedCaptainException
 *     businessService.assignCaptain();
 * } catch (DuplicatedCaptainException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error assigning captain: " + e.getMessage(), e);
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
public class DuplicatedCaptainException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 6009648072538957865L;

    /**
     * Constructs a new DuplicatedCaptainException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DuplicatedCaptainException(String message) {
        super(message);
    }

    /**
     * Constructs a new DuplicatedCaptainException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception}    The cause (which is saved for later retrieval by the getCause() method)
     */
    public DuplicatedCaptainException(String message, Exception cause) {
        super(message, cause);
    }
}
