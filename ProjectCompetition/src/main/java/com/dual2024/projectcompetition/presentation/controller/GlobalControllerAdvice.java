package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.presentation.exception.Body;
import com.dual2024.projectcompetition.presentation.exception.NotFoundException;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The  Global controller advice.
 *
 * @author Franciosco Balonero Olivera
 */
@RestControllerAdvice
public class GlobalControllerAdvice {
    /**
     * Handle presentation exception response entity.
     *
     * @param ex The PresentationException
     * @return The response entity
     */
    @ExceptionHandler(PresentationException.class)
    public ResponseEntity<Body> handlePresentationException(Exception ex) {
        Body error = new Body(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handle not found exception response entity.
     *
     * @param ex the NotFoundException
     * @return The response entity
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Body> handleNotFoundException(Exception ex) {
        Body error = new Body(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handle validation errors response entity.
     *
     * @param ex The MethodArgumentNotValidException
     * @return The response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Body> handleValidationErrors(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        for (FieldError field : ex.getBindingResult().getFieldErrors()) {
            message.append(field.getField()).append(" ").append(field.getDefaultMessage());
        }
        Body error = new Body(HttpStatus.BAD_REQUEST, message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    /**
     * Handle access denied exception exception response entity.
     *
     * @param ex The ex
     * @return The response entity
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Body> handleAccessDeniedExceptionException(Exception ex) {
        Body error = new Body(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

}
