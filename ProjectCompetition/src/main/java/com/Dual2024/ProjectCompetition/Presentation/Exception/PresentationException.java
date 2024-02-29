package com.Dual2024.ProjectCompetition.Presentation.Exception;

public class PresentationException extends RuntimeException {

	private static final long serialVersionUID = -1641575469051626634L;

	public PresentationException(String message) {
		super(message);
	}

	public PresentationException(String message, Exception e) {
		super(message, e);
	}
}
