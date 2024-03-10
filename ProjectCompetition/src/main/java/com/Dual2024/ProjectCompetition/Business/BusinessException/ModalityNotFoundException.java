package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

/**
 * Exception indicating that a modality is not found.
 *
 * <p>This exception is thrown to indicate that a business operation cannot be performed
 * because the specified modality was not found.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * try {
 *     // Business operation that may throw ModalityNotFoundException
 *     businessService.findModality();
 * } catch (ModalityNotFoundException e) {
 *     // Handle the exception appropriately
 *     logger.error("Error finding modality: " + e.getMessage(), e);
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
public class ModalityNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -115495127483418668L;

    /**
     * Constructs a new ModalityNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ModalityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ModalityNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public ModalityNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
