package com.dual2024.projectcompetition.presentation.exception;

import java.io.Serial;

/**
 * Generic exception in the presentation layer.
 *
 * <p>This exception is a runtime exception indicating an issue in the presentation layer of the application.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Code that may throw PresentationException
 *     presentationService.performOperation();
 * } catch (PresentationException e) {
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
public class PresentationException extends RuntimeException {

    /**
     * Serial version UID for serialization.
     */
    @Serial
    private static final long serialVersionUID = -1641575469051626634L;

    /**
     * Constructs a new PresentationException with the specified detail message.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public PresentationException(String message) {
        super(message);
    }

    /**
     * Constructs a new PresentationException with the specified detail message and cause.
     *
     * @param message {@link String} The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   {@link Exception} The cause (which is saved for later retrieval by the getCause() method)
     */
    public PresentationException(String message, Exception cause) {
        super(message, cause);
    }
}
