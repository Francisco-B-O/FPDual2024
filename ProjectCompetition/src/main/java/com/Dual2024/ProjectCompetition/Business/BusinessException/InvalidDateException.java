package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class InvalidDateException extends BusinessException {

	private static final long serialVersionUID = 3800928255694765328L;

	public InvalidDateException(String message) {
		super(message);
	}

	public InvalidDateException(String message, Exception e) {
		super(message, e);
	}
}
