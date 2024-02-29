package com.Dual2024.ProjectCompetition.Presentation.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
	@ExceptionHandler(PresentationException.class)
	public ResponseEntity<Body> genericRequest(Exception ex) {
		Body error = new Body(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Body> notFoundRequest(Exception ex) {
		Body error = new Body(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Body> handleValidationErrors(MethodArgumentNotValidException ex) {
		String message = "";
		for (FieldError field : ex.getBindingResult().getFieldErrors()) {
			message += field.getField() + " " + field.getDefaultMessage() + " || ";
		}
		Body error = new Body(HttpStatus.BAD_REQUEST, message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

}
