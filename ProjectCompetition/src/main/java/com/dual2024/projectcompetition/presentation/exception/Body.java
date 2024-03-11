package com.Dual2024.ProjectCompetition.Presentation.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents the body of an error response in the presentation layer.
 *
 * <p>This class includes information such as the HTTP status, timestamp, and error message.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * Body errorBody = new Body(HttpStatus.NOT_FOUND, "Resource not found");
 * }
 * </pre>
 *
 * <p>The {@code date} field is annotated with {@link JsonFormat} to specify the format in which the timestamp
 * should be serialized when converting the object to JSON.</p>
 *
 * <p>The class provides both a parameterized constructor for creating an instance with a specific HTTP status
 * and message, and a no-argument constructor that sets the timestamp to the current date and time.</p>
 *
 * @author Franciosco Balonero Olivera
 */
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Body {

    /**
     * HTTP status of the error.
     */
    @NonNull
    private HttpStatus state;

    /**
     * Timestamp of when the error occurred.
     */
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    /**
     * Error message.
     */
    @NonNull
    private String message;
}
