package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Generic business layer exception.
 *
 * <p>This exception serves as a base class for custom exceptions in the business layer.
 * It provides a way to handle and communicate business-specific errors within the application.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw BusinessException
 *     businessService.performOperation();
 * } catch (BusinessException e) {
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
 */
public class BusinessException extends Exception {

    @Serial
    private static final long serialVersionUID = -1618850050663333984L;

    /**
     * Constructs a new BusinessException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructs a new BusinessException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public BusinessException(String message, Exception cause) {
        super(message, cause);
    }
}
