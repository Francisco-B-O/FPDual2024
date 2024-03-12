package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.presentation.exception.Body;
import com.dual2024.projectcompetition.presentation.exception.NotFoundException;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global controller advice for handling exceptions across all controllers.
 *
 * <p>This class provides exception handling mechanisms for various exceptions that may occur during
 * the processing of HTTP requests. It is responsible for translating exceptions into appropriate
 * HTTP responses with meaningful error messages.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see Body
 */
@RestControllerAdvice
@Tag(name = "Advice", description = "Response error")
public class GlobalControllerAdvice {

    /**
     * Handles PresentationException and returns a ResponseEntity with a corresponding error message.
     *
     * @param ex The PresentationException
     * @return The ResponseEntity containing the error details
     */
    @Operation(summary = "Handle PresentationException", description = "Handles PresentationException and returns a ResponseEntity with a corresponding error message.")
    @ExceptionHandler(PresentationException.class)
    public ResponseEntity<Body> handlePresentationException(Exception ex) {
        Body error = new Body(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles NotFoundException and returns a ResponseEntity with a corresponding error message.
     *
     * @param ex The NotFoundException
     * @return The ResponseEntity containing the error details
     */
    @Operation(summary = "Handle NotFoundException", description = "Handles NotFoundException and returns a ResponseEntity with a corresponding error message.")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Body> handleNotFoundException(Exception ex) {
        Body error = new Body(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with validation error details.
     *
     * @param ex The MethodArgumentNotValidException
     * @return The ResponseEntity containing the validation error details
     */
    @Operation(summary = "Handle Validation Errors", description = "Handles MethodArgumentNotValidException and returns a ResponseEntity with validation error details.")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Body> handleValidationErrors(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        for (FieldError field : ex.getBindingResult().getFieldErrors()) {
            message.append(" ").append(field.getDefaultMessage());
        }
        Body error = new Body(HttpStatus.BAD_REQUEST, message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles AccessDeniedException and returns a ResponseEntity with a corresponding error message.
     *
     * @param ex The AccessDeniedException
     * @return The ResponseEntity containing the error details
     */
    @Operation(summary = "Handle Access Denied Exception", description = "Handles AccessDeniedException and returns a ResponseEntity with a corresponding error message.")
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Body> handleAccessDeniedExceptionException(Exception ex) {
        Body error = new Body(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
