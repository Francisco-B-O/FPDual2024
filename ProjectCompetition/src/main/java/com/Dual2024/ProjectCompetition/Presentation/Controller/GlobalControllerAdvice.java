package com.Dual2024.ProjectCompetition.Presentation.Controller;

import com.Dual2024.ProjectCompetition.Presentation.Exception.Body;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(PresentationException.class)
    public ResponseEntity<Body> handlePresentationException(Exception ex) {
        Body error = new Body(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Body> handleNotFoundException(Exception ex) {
        Body error = new Body(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Body> handleValidationErrors(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        for (FieldError field : ex.getBindingResult().getFieldErrors()) {
            message.append(field.getField()).append(" ").append(field.getDefaultMessage());
        }
        Body error = new Body(HttpStatus.BAD_REQUEST, message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Body> handleAccesDeniedExceptionException(Exception ex) {
        Body error = new Body(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

}
