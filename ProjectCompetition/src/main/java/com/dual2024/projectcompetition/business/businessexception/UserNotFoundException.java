package com.dual2024.projectcompetition.business.businessexception;

import java.io.Serial;

/**
 * Exception indicating that a user is not found.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * * because the specified user was not found.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw UserNotFoundException
 *     businessService.performOperation();
 * } catch (UserNotFoundException e) {
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
public class UserNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -5004348401632046351L;

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception}  The cause (which is saved for later retrieval by the getCause() method)
     */
    public UserNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
