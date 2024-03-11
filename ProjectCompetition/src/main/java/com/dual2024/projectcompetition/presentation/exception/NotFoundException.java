package com.Dual2024.ProjectCompetition.Presentation.Exception;

import java.io.Serial;

/**
 * Exception indicating that the requested resource was not found.
 *
 * <p>This exception is a specific type of {@link PresentationException} that is thrown to indicate that
 * a requested resource, such as a user, team, or tournament, could not be found.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Code that may throw NotFoundException
 *     presentationService.findResource();
 * } catch (NotFoundException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error finding the resource: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The exception includes a serial version UID for serialization purposes. It provides
 * two constructors, one with a message and another with both a message and a cause.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see PresentationException
 */
public class NotFoundException extends PresentationException {

    /**
     * Serial version UID for serialization.
     */
    @Serial
    private static final long serialVersionUID = -7198817472286130251L;

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new NotFoundException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public NotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
