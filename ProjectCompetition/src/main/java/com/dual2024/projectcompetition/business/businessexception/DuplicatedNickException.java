package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that the nickname is already in use in a user.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because there is an attempt to register a nickname that is already use.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw DuplicatedNickException
 *     businessService.createUser();
 * } catch (DuplicatedNickException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error creating user: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The exception includes a serial version UID for serialization purposes. It provides
 * two constructors, one with a message and another with both a message and a cause.</p>
 *
 * @author Franciso Balonero Olivera
 * @see BusinessException
 */
public class DuplicatedNickException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 5235794232694134448L;

    /**
     * Constructs a new DuplicatedNickException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DuplicatedNickException(String message) {
        super(message);
    }

    /**
     * Constructs a new DuplicatedNickException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception}    The cause (which is saved for later retrieval by the getCause() method)
     */
    public DuplicatedNickException(String message, Exception cause) {
        super(message, cause);
    }
}
