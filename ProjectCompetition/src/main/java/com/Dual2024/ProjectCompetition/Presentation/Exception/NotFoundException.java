package com.Dual2024.ProjectCompetition.Presentation.Exception;

public class NotFoundException extends PresentationException {

	private static final long serialVersionUID = -7198817472286130251L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Exception e) {
		super(message, e);
	}
}
