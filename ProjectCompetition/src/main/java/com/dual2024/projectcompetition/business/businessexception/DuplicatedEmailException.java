package com.Dual2024.ProjectCompetition.business.business_exception;

import java.io.Serial;

/**
 * Exception indicating the email already exists in a user.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because there is an attempt to register an email that already use.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw DuplicatedEmailException
 *     businessService.registerUser();
 * } catch (DuplicatedEmailException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error registering user: " + e.getMessage(), e);
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
public class DuplicatedEmailException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 151017868746738093L;

    /**
     * Constructs a new DuplicatedEmailException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DuplicatedEmailException(String message) {
        super(message);
    }

    /**
     * Constructs a new DuplicatedEmailException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public DuplicatedEmailException(String message, Exception cause) {
        super(message, cause);
    }
}
