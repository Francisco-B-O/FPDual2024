package com.Dual2024.ProjectCompetition.DataAccess.DataException;

import java.io.Serial;

/**
 * Custom exception class indicating that the requested entity was not found.
 *
 * <p>This exception is a subtype of {@link DataException} and is intended to be thrown when an entity is not found during data access operations.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Data access operation to find an entity
 *     // ...
 *     if (entity == null) {
 *         throw new EntityNotFoundException("Entity not found");
 *     }
 * } catch (EntityNotFoundException e) {
 *     // Handle the exception
 *     logger.error("Entity not found: " + e.getMessage(), e);
 * }
 * }
 * </pre>
 *
 * <p>The exception includes both a message describing the error and, if available, the original cause of the exception.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see DataException
 */
public class EntityNotFoundException extends DataException {

    @Serial
    private static final long serialVersionUID = 8280183600637864595L;

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new EntityNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public EntityNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
